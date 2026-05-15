# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**LiveTracker** is a real-time Facebook Live comment tracker. It connects to the Facebook Graph API, collects comments from live streams, categorizes them by action type (BUY, REVIEW, QUESTION), and presents them in a web UI for validation/management during live commerce sessions. It supports contest mode and real-time sales/inventory tracking.

## Build & Run Commands

**Always build from root** — the backend's `processResources` task depends on `:frontend:browserWebpack`, so building `:backend` alone without building frontend first will fail.

```bash
./gradlew build                          # Full build of all modules
./gradlew -p backend quarkusDev         # Backend dev server with hot reload (port 8080)
./gradlew test                           # All tests
./gradlew :backend:test                  # Backend tests only
./gradlew :core:commonTest              # Core shared tests only
```

**Single module builds (requires prior frontend build):**
```bash
./gradlew :core:build
./gradlew :frontend:build
./gradlew :backend:build                 # Only after frontend is built
```

**Native image:**
```bash
./gradlew build -Dquarkus.package.type=native
```

No Facebook credentials are needed for local development — the `dev` and `test` profiles use an embedded mock server.

### Validation loop

Run this loop before reporting a task complete. Each step is a gate — stop and fix on failure rather than proceeding.

1. **Clean compile** — catches stale outputs masking real errors:
   ```bash
   ./gradlew clean build -x test
   ```
2. **Tests** — run all modules together so cross-module breakage surfaces:
   ```bash
   ./gradlew test
   ```
   If a single module's tests fail, re-run just that module (`:core:commonTest`, `:backend:test`) for faster iteration, but always re-run the full `test` before declaring done.
3. **Frontend bundle sanity** — verify webpack output actually landed in backend resources:
   ```bash
   ls backend/build/resources/main/META-INF/resources/ | head
   ```
   Missing/empty means `:frontend:browserWebpack` didn't run — rebuild from root.
4. **Dev smoke test** — start the backend and hit a known endpoint:
   ```bash
   ./gradlew -p backend quarkusDev &
   curl -sf http://localhost:8080/q/health   # Quarkus health probe
   curl -sf http://localhost:8080/           # frontend served
   ```
   For UI-affecting changes, open `http://localhost:8080` in a browser and exercise the changed flow — type checks and tests don't verify UI behavior.
5. **Native build** (only when touching reflection, serialization, or build config) — these break native-only:
   ```bash
   ./gradlew build -Dquarkus.package.type=native
   ```

If a step fails with a "works on my last run" symptom (cached resources, leftover `/tmp/{videoId}.out` files, stale `kotlin-js-store/`), redo from step 1 with `clean`. Don't paper over flakes — they usually indicate the dependency between `:frontend:browserWebpack` and `:backend:processResources` didn't resolve.

## Architecture

Three Gradle modules with a strict dependency order:

```
core  →  backend
  ↘         ↑
    frontend (output copied into backend resources)
```

### `core` — Kotlin Multiplatform (JVM + JS)

Shared domain models compiled for both JVM (backend) and JS (frontend). Key types:
- `LiveEvent` — sealed event with subtypes: `COMMENT.{BUY,REVIEW,QUESTION,NOTHING}`, `CONTEST.{SWITCH,COMMENT}`
- `Comment` — Facebook comment (user, timestamp, message, parsed `ActionType`)
- `Contest`, `Customer`, `Sku` — supporting domain entities
- All types are `@Serializable` (kotlinx-serialization)

### `backend` — Quarkus JVM

**Infrastructure layer** (`infrastructure/facebook/`):
- `VideoStream.kt` — Quarkus REST client that calls the Facebook Graph API (or mock in dev)
- `FacebookCollector.kt` — Streams and parses comments; keyword matching classifies `ActionType`; writes JSON lines to `/tmp/{videoId}.out`

**Domain layer** (`domain/`):
- `live_tracking/` — `LiveTrackingService` orchestrates start/stop; `ValidationService` manages comment state and contest logic
- `sales/InventoryService` — tracks items sold
- `customer/CustomerService` and `review/ReviewService` — secondary features

**API layer:**
- `LiveTrackerApi` — REST endpoints for starting/stopping a live session (`POST/DELETE/GET /live`)
- `LiveTrackerWSApi` — WebSocket endpoint that broadcasts `LiveEvent`s to connected frontend clients
- `SalesApi`, `CustomerApi`, `ReviewApi` — domain-specific REST endpoints
- Quarkus event bus (`@ConsumeEvent`) connects infrastructure → domain → API layers

### `frontend` — Kotlin/JS (React + MUI)

React application written in Kotlin/JS using Material-UI 5 components and Emotion for styling. Webpack bundles the output into `frontend/build/distributions/`, which is then copied into backend resources.

## Configuration Profiles

Backend uses Quarkus profiles defined in `backend/src/main/resources/application.properties`:

| Profile | Facebook endpoint | Log level |
|---------|------------------|-----------|
| `dev`   | Mock server (localhost) | DEBUG |
| `test`  | Mock server (faster rate) | DEBUG |
| `prod`  | Real Facebook Graph API | INFO |

The mock server (`MockServer.kt`) runs embedded within Quarkus in dev/test and simulates a realistic comment stream — no real credentials needed.

## Comment Classification Keywords

Keywords are French (the app targets French-speaking live commerce):
- `prend`, `achet`, `achèt` → `ActionType.BUY`
- `revoir` → `ActionType.REVIEW`
- `?` → `ActionType.QUESTION`
- Anything else → `ActionType.NOTHING`

Numbers in comment text are extracted as potential product item IDs.

## Key Versions

Defined in `gradle.properties`:
- Kotlin: 1.6.21
- Quarkus: 2.11.1
- React: 18.0.0-pre.332
- MUI: 5.6.2
- Ktor: 2.1.2
- Java target: JVM 11

## CI/CD

Two GitHub Actions workflows:
- `integration_build.yml` — runs `./gradlew build` on every push touching `backend/`, `core/`, or `frontend/`
- `build_and_release.yml` — triggered on release; publishes JVM and native Docker images to `ghcr.io/alexgenon/facebook-live-tracker` (multi-arch: amd64 + arm64)

## Notes

- `backend/README.md` references Maven — ignore it. This project is **Gradle-only**.
- `kotlin-js-store/` is auto-generated by the Kotlin/JS webpack plugin — do not manually edit it.
- Kubernetes deployment manifests are in `backend/src/main/resources/kubernetes/`.
- OpenTelemetry and Prometheus metrics are wired in but disabled by default (`application.properties`).
- The repository is now under git with full history available — use `git log`, `git blame`, and `git diff` to investigate past changes and motivation. (When this file was first generated via `/init`, the project was not yet a git repository.)
