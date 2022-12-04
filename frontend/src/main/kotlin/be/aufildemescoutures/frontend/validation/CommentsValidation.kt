package be.aufildemescoutures.frontend.validation

import CommentsToValidate
import be.aufildemescoutures.domain.live_tracking.core.comment.ActionType
import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import be.aufildemescoutures.domain.live_tracking.core.comment.CommentForContest
import be.aufildemescoutures.domain.live_tracking.core.comment.ContestManagement
import be.aufildemescoutures.domain.live_tracking.core.live_event.LiveEvent
import be.aufildemescoutures.frontend.ServerConfig
import be.aufildemescoutures.frontend.ServerStatus
import csstype.*
import kotlinx.datetime.Clock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mui.lab.TabContext
import mui.lab.TabPanel
import mui.material.*
import mui.system.sx
import org.w3c.dom.WebSocket
import react.*


external interface CommentsValidationProps : Props {
    var serverConfig: ServerConfig
    var serverStatus: ServerStatus
    var contestSwitch: (ContestManagement) -> Unit
}

val CommentsValidation = FC<CommentsValidationProps> { props ->
    val (commentsList, updateCommentsList) = useState(emptyList<Comment>())
    var webSocket: WebSocket? by useState(null)
    val (contestComments, updateContestComments) = useState(emptyList<CommentForContest>())
    var selectedTab: String by useState("Comments")

    val newEvent: (events: List<LiveEvent>) -> Unit = { events ->
        events.forEach { event ->
            when (event.eventType) {
                LiveEvent.commentToValidate -> updateCommentsList.invoke { prevList -> (prevList + event.comment()) }
                LiveEvent.commentValidated -> updateCommentsList.invoke { prevList -> prevList.filterNot { it == event.comment() } }
                LiveEvent.contestSwitch -> {
                    props.contestSwitch(event.contest())
                    updateContestComments.invoke { _ -> emptyList() }
                }
                LiveEvent.contestComment -> updateContestComments.invoke { prevList -> prevList + event.commentForContest() }
                else -> console.warn("Received unsupported event: $event")
            }
        }
    }

    useEffectOnce { webSocket = getCommentsPendingValidationJS(props.serverConfig, newEvent) }

    TabContext {
        value = selectedTab
        Box {
            sx {
                borderBottom = 1.px
                borderColor = js("'divider'")
            }
            Tabs {
                onChange = { _, newTab -> selectedTab = newTab }
                value = selectedTab
                Tab {
                    label = ReactNode("Commentaires")
                    value = "Comments"
                }
                Tab {
                    label = ReactNode("Concours ${if (props.serverStatus.contestOngoing()) "*" else ""}")
                    value = "Contest"
                    //disabled = ( contest == ContestManagement.NoContest)
                }
            }
        }
        TabPanel {
            value = "Comments"
            tabIndex = 0
            CommentsToValidate {
                serverConfig = props.serverConfig
                comments = commentsList
                commentValidated = { comment: Comment, action: ActionType ->
                    validateComment(comment, action, webSocket)
                    { c -> updateCommentsList.invoke { prevList -> prevList.filterNot { it == c } } }
                }

            }
        }

        TabPanel {
            value = "Contest"
            tabIndex = 1
            ContestValidation{
                comments = contestComments
            }
        }
    }
}


fun getCommentsPendingValidationJS(serverConfig: ServerConfig, processEvents: (List<LiveEvent>) -> Unit): WebSocket {
    console.log("${Clock.System.now()} : call to getCommentsPendingValidationJS")
    val ws = WebSocket(serverConfig.getFullWSURL() + "/live/comments/validation")
    ws.onmessage = { processEvents(Json.decodeFromString(it.data as String)) }
    return ws
}

fun validateComment(comment: Comment, updatedAction: ActionType, ws: WebSocket?, triggerRemoval: (Comment) -> Unit) {
    ws?.send(Json.encodeToString(LiveEvent.build(comment.copy(action = updatedAction), LiveEvent.commentValidated)))
    if (ws == null) {
        console.warn("No Websocket, cannot send update")
    }
    triggerRemoval(comment)
}

/*suspend fun getCommentsPendingValidationKtor(serverConfig: ServerConfig) : Flow<List<Comment>>{
    val client = HttpClient() {
        install(WebSockets) {
            contentConverter = KotlinxWebsocketSerializationConverter(Json)
        }
    }

    return flow {
        client.webSocket(method = HttpMethod.Get,
            serverConfig.serverUrl,
            port = serverConfig.port,
            path = "/live/comments/validation") {
            while (true) {
                val receivedMessage = incoming.receive() as? Frame.Text
                receivedMessage?.let {
                    val receivedEvents = Json.decodeFromString<List<LiveEvent>>(receivedMessage.readText())
                    emit(receivedEvents.map { it.comment })
                }
            }
        }
    }
}*/
