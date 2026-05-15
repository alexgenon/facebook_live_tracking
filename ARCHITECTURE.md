# Architecture

## Module Structure

The project is a Kotlin Multiplatform monorepo with three Gradle modules. The build order is strict: `core` is compiled first (to JVM and JS), `frontend` consumes the JS output, and `backend` bundles the frontend's webpack output into its own resources.

```mermaid
---
config:
  look: handDrawn
---
graph LR
    core["core<br/>(Kotlin Multiplatform)"]
    backend["backend<br/>(Quarkus JVM)"]
    frontend["frontend<br/>(Kotlin/JS + React)"]

    core -->|JVM jar| backend
    core -->|JS output| frontend
    frontend -->|webpack bundle| backend
```

---

## High-Level Component Overview

```mermaid
---
config:
  look: handDrawn
---
graph TB
    FB["Facebook Graph API<br/>streaming-graph.facebook.com<br/>(SSE stream)"]
    MOCK["MockServer<br/>/mock<br/>(dev/test only)"]

    subgraph backend["Backend – Quarkus JVM :8080"]
        FC["FacebookCollector"]
        VS["VideoStream<br/>REST client"]
        LTS["LiveTrackingService"]
        VS2["ValidationService"]
        IS["InventoryService"]
        CS["CustomerService"]
        RS["ReviewService"]
        VR["ValidationRepository"]
        CR["CustomerRepository"]
        EB@{ shape: das, label: "Quarkus<br/>Event Bus" }
        WS["LiveTrackerWSApi<br/>WebSocket /live/comments/validation"]
        REST["REST APIs<br/>/live  /sales  /customers  /reviews"]
    end

    subgraph frontend["Frontend – Kotlin/JS React"]
        APP["App"]
        CC["ControlCenter"]
        CV["CommentsValidation"]
        CTV["CommentsToValidate"]
        CON["ContestValidation"]
    end

    FB -->|SSE comments| VS
    MOCK -.->|SSE comments dev/test| VS
    VS --> FC --> LTS
    LTS --> EB
    EB --> VS2
    EB --> CS
    VS2 --> EB
    EB --> IS
    EB --> RS
    VS2 --> VR
    CS --> CR
    WS <-->|"LiveEvent (JSON)"| EB
    REST --> LTS
    REST --> VS2
    REST --> IS
    REST --> CS
    REST --> RS

    APP --> CC
    APP --> CV
    CV --> CTV
    CV --> CON
    CC <-->|"REST (start/stop)"| REST
    CV <-->|"WebSocket"| WS
    CTV <-->|"REST (archives)"| REST
```

---

## Domain Model

```mermaid
classDiagram
    class LiveEvent {
        +EventType eventType
        +EventPayload payload
    }

    class EventPayload {
        <<sealed interface>>
    }

    class CommentPayload {
        +Comment comment
    }

    class CommentForContestPayload {
        +CommentForContest commentForContest
    }

    class ContestPayload {
        +ContestManagement contest
    }

    class Comment {
        +String commentId
        +Customer user
        +Int item
        +Instant timestamp
        +String fullComment
        +ActionType action
    }

    class ActionType {
        <<enum>>
        BUY
        REVIEW
        QUESTION
        NOTHING
        CONTEST
    }

    class Customer {
        <<sealed>>
    }

    class FacebookUser {
        +String name
        +CustomerId id
    }

    class NoRecordedUser

    class ContestManagement {
        <<sealed>>
    }

    class Contest {
        +String keyword
        +String normalizedKeyword
        +Boolean isNumber
    }

    class NoContest

    class CommentForContest {
        +Comment comment
        +Contest contest
        +Float score
        +Boolean isAMatch
    }

    EventPayload <|-- CommentPayload
    EventPayload <|-- CommentForContestPayload
    EventPayload <|-- ContestPayload
    LiveEvent --> EventPayload

    Comment --> ActionType
    Comment --> Customer
    Customer <|-- FacebookUser
    Customer <|-- NoRecordedUser
    ContestManagement <|-- Contest
    ContestManagement <|-- NoContest
    CommentForContest --> Comment
    CommentForContest --> Contest
```

---

## Event Bus Message Flow

The Quarkus Event Bus is the primary internal communication mechanism. All event names are string constants defined in `LiveEvent`.

```mermaid
flowchart TD
    FB["Facebook SSE Stream"]
    FC["FacebookCollector\n.collectComments()"]
    LTS["LiveTrackingService"]

    EB_NEW(["COMMENT.NEW"])
    EB_VALIDATE(["COMMENT.TO_VALIDATE"])
    EB_CONTEST_C(["CONTEST.COMMENT"])
    EB_VALIDATED(["COMMENT.VALIDATED"])
    EB_BUY(["COMMENT.BUY"])
    EB_REVIEW(["COMMENT.REVIEW"])
    EB_CONTROL(["LIVE.CONTROL / STOP"])
    EB_CONTEST_SW(["CONTEST.SWITCH"])

    VS2["ValidationService"]
    CS["CustomerService"]
    IS["InventoryService"]
    RS["ReviewService"]
    WSAPI["LiveTrackerWSApi\n(WebSocket)"]

    FE["Frontend WebSocket client"]

    FB -->|SSE| FC --> LTS
    LTS -->|publish| EB_NEW
    EB_NEW --> VS2
    EB_NEW --> CS

    VS2 -->|contest inactive| EB_VALIDATE --> WSAPI
    VS2 -->|contest active| EB_CONTEST_C --> WSAPI

    WSAPI -->|broadcast LiveEvent| FE

    FE -->|send LiveEvent.commentValidated| WSAPI
    WSAPI -->|publish| EB_VALIDATED
    EB_VALIDATED --> VS2

    VS2 -->|action == BUY| EB_BUY --> IS
    VS2 -->|action == REVIEW or QUESTION| EB_REVIEW --> RS

    LTS -->|stopTracking| EB_CONTROL --> VS2

    REST_CONTEST["REST POST /live/comments/validation/contest"]
    REST_CONTEST -->|startContestMode| VS2
    VS2 -->|publish| EB_CONTEST_SW --> WSAPI
```

---

## REST API

```mermaid
graph LR
    subgraph live["/live"]
        L1["POST /\nstart tracking\n(video, liveId, items)"]
        L2["DELETE /\nstop tracking"]
        L3["GET /\nlive status"]
        L4["GET /comments/validation/list\npending comments"]
        L5["POST /comments/validation/contest\nstart contest (keyword)"]
        L6["DELETE /comments/validation/contest\nstop contest"]
        L7["POST /comments/validation/{id}\nvalidate comment (action)"]
        L8["GET /comments/archives/\nall archived comments"]
        L9["GET /comments/archives/{name}\ncomments for customer"]
    end

    subgraph sales["/sales"]
        S1["GET /\ncurrent inventory"]
        S2["GET /as_string\nsales report"]
    end

    subgraph customers["/customers"]
        C1["GET /\nall customers"]
        C2["GET /{name}\ncustomer by name"]
    end

    subgraph reviews["/reviews"]
        R1["GET /items/stream\nSSE: item IDs to review"]
        R2["POST /items/{id}/reviewed"]
        R3["GET /questions/stream\nSSE: questions"]
        R4["POST /questions/{id}/answered"]
    end
```

---

## WebSocket Protocol

The single WebSocket endpoint (`/live/comments/validation`) carries `LiveEvent` objects serialized as JSON.

```mermaid
sequenceDiagram
    participant FE as Frontend
    participant WS as LiveTrackerWSApi
    participant EB as Event Bus
    participant VS as ValidationService

    FE->>WS: connect
    WS->>FE: send List<LiveEvent> (all pending comments)

    Note over EB,WS: New comment arrives from Facebook

    EB->>WS: COMMENT.TO_VALIDATE (Comment)
    WS->>FE: broadcast [LiveEvent{commentToValidate, comment}]

    Note over FE,VS: User validates a comment in UI

    FE->>WS: send LiveEvent{commentValidated, comment}
    WS->>EB: publish COMMENT.VALIDATED (comment)
    EB->>VS: commentValidated()
    VS->>EB: COMMENT.BUY or COMMENT.REVIEW
    WS->>FE: broadcast [LiveEvent{commentValidated, comment}]

    Note over FE,WS: Contest lifecycle

    FE->>WS: REST POST /live/comments/validation/contest
    EB->>WS: CONTEST.SWITCH (ContestManagement)
    WS->>FE: broadcast [LiveEvent{contestSwitch, contest}]

    EB->>WS: CONTEST.COMMENT (CommentForContest)
    WS->>FE: broadcast [LiveEvent{contestComment, commentForContest}]
```

---

## Comment Processing Pipeline

```mermaid
flowchart LR
    RAW["Raw Facebook SSE\n{from.name, from.id,\ncreated_time, message}"]

    subgraph FC["FacebookCollector"]
        PARSE["Parse JSON\n→ extract fields"]
        NUMS["Extract item numbers\nfrom message text"]
        CLASSIFY["Classify ActionType\nprend/achet/achèt → BUY\nrevoir → REVIEW\n? → QUESTION\nelse → NOTHING"]
        PERSIST["Write JSON line\nto /tmp/{videoId}.out"]
    end

    COMMENT["Comment(s)\none per item number found"]

    RAW --> PARSE --> NUMS --> CLASSIFY --> PERSIST --> COMMENT
```

When a comment contains multiple item numbers (e.g., "je prends 3 et 7"), one `Comment` is produced per number, each with a different `item` value.

---

## Frontend Component Tree

```mermaid
graph TD
    APP["App\n(manages: serverStatus, theme)\n(calls: getLiveStatus on mount)"]

    CC["ControlCenter\n(inputs: liveId, fbVideoId,\ninventorySize, contestKeyword)\n(calls: toggleLive, toggleContest via REST)"]

    CV["CommentsValidation\n(manages: commentsList, contestComments)\n(opens WebSocket on mount)"]

    CTV["CommentsToValidate\n(shows pending comments table)\n(sends validation via WebSocket)\n(loads user history via REST)"]

    CON["ContestValidation\n(shows CommentForContest list)\n(highlights matches: score ≥ 0.9)"]

    VA["ValidationActions\n(ActionType dropdown)"]

    APP --> CC
    APP --> CV
    CV --> CTV
    CV --> CON
    CTV --> VA
```

---

## Deployment

```mermaid
graph TB
    subgraph k8s["Kubernetes"]
        DEP["live-tracker Deployment\n(JVM or native image)"]
        SVC["Service"]
        SEC["facebook-secrets\n(page_access_token)"]
        SM["ServiceMonitor\n(Prometheus scrape)"]
        OT["OpenTelemetry Collector"]
    end

    GHCR["ghcr.io/alexgenon/facebook-live-tracker\n:latest / :native-latest"]

    GHCR -->|pull| DEP
    SEC -->|env inject| DEP
    DEP --> SM
    DEP -->|OTLP traces| OT
    SVC --> DEP
```

Docker images are built by the `build_and_release.yml` GitHub Actions workflow on each GitHub Release, targeting `linux/amd64` and `linux/arm64`.

---

## Technology Stack Summary

| Layer | Technology |
|---|---|
| Language | Kotlin 1.6.21 (JVM + JS multiplatform) |
| Backend framework | Quarkus 2.11.1 |
| Reactive streams | Mutiny (`Multi<T>`) |
| Internal messaging | Quarkus Event Bus (Vert.x) |
| HTTP client (Facebook) | Quarkus REST Client Reactive |
| WebSocket (server) | `quarkus-websockets` (JSR-356) |
| Serialization | `kotlinx-serialization-json` |
| Frontend UI | React 18 + Material-UI 5 (Kotlin/JS wrappers) |
| CSS-in-JS | Emotion |
| HTTP client (frontend) | Browser `fetch` / `WebSocket` |
| Build | Gradle 7.5.1 |
| Container registry | GitHub Container Registry (`ghcr.io`) |
| Observability | Micrometer + Prometheus, OpenTelemetry (OTLP) |
