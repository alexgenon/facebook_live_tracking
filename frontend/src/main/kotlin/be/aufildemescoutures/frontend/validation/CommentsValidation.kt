package be.aufildemescoutures.frontend.validation

import be.aufildemescoutures.domain.live_tracking.core.comment.ActionType
import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import be.aufildemescoutures.domain.live_tracking.core.live_event.LiveEvent
import be.aufildemescoutures.frontend.ServerConfig
import be.aufildemescoutures.frontend.controls.ServerStatus
import be.aufildemescoutures.frontend.controls.eventToInputValue
import be.aufildemescoutures.frontend.controls.mainScope
import csstype.Color
import csstype.JustifyContent
import csstype.em
import csstype.pc
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mui.icons.material.HighlightOffOutlined
import mui.icons.material.UpdateOutlined
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import org.w3c.dom.WebSocket
import react.*
import react.dom.html.InputType
import react.dom.html.ReactHTML.em
import react.dom.onChange


external interface CommentsToValidateProps : Props {
    var serverConfig: ServerConfig
    var serverStatus: ServerStatus
}

val CommentsToValidate = FC<CommentsToValidateProps> { props ->
    val (commentsList, updateCommentsList) = useState(emptyList<Comment>())
    var webSocket: WebSocket? by useState(null)
    var allCommentsForUser: List<Comment> by useState(emptyList())
    var bulkValidationCount:Int by useState(30)

    val newEvent : (events:List<LiveEvent>)->Unit = { events ->
        events.forEach { event ->
            when (event.eventType){
                LiveEvent.commentToValidate -> updateCommentsList.invoke { prevList -> (prevList + event.comment()) }
                LiveEvent.commentValidated  -> updateCommentsList.invoke { prevList -> prevList.filterNot{ it == event.comment() } }
                else -> console.warn("Received unsupported event: $event")
            }
        }
    }

    useEffectOnce {
        webSocket = getCommentsPendingValidationJS(props.serverConfig, newEvent)
    }
    Typography {
        variant = TypographyVariant.h2
        +"${commentsList.size} commentaires à revoir"
    }

    Stack {
        direction = responsive(StackDirection.row)
        spacing = responsive(2)
        sx {
            justifyContent = JustifyContent.spaceBetween
            padding = 3.em
        }
        Stack {
            direction = responsive (StackDirection.column)
            spacing = responsive(2)
            Box{
                Button {
                    +"Valider $bulkValidationCount commentaires"
                    onClick = { _ ->
                        commentsList
                            .take(bulkValidationCount)
                            .forEach {
                                validateComment(it, it.action, webSocket)
                                { comment -> updateCommentsList.invoke { prevList -> prevList.filterNot { it == comment } } }
                            }
                    }
                }
                TextField {
                    label = ReactNode("Quantité validation en masse")
                    variant = FormControlVariant.outlined
                    defaultValue = bulkValidationCount.toString()
                    type = InputType.number
                    onChange =
                        { bulkValidationCount = eventToInputValue(it).toInt() }
                }
            }
            TableContainer {
                component = Paper.create().type

                Table {
                    size = Size.small
                    TableHead {
                        TableRow {
                            TableCell {
                                sx { width = 30.pc }
                                +"Nom"
                            }
                            TableCell {
                                sx { width = 3.pc }
                                +"Hist."
                            }
                            TableCell {
                                sx { width = 4.pc }
                                +"Item"
                            }
                            TableCell {
                                sx { width = 8.pc }
                                +"Action"
                            }
                            TableCell {
                                +"Commentaire"
                            }
                        }
                    }
                    TableBody {
                        commentsList.map { comment ->
                            TableRow {
                                key = comment.id.toString()
                                TableCell {
                                    +comment.user.fullName()
                                    onClick = { _ -> window.navigator.clipboard.writeText(comment.user.fullName()) }
                                    sx {
                                        hover {
                                            backgroundColor = Color("#b9c78e")
                                        }
                                    }
                                }
                                TableCell {
                                    UpdateOutlined {
                                        onClick = { _ ->
                                            if (allCommentsForUser.isEmpty()
                                                || comment.user != allCommentsForUser[1].user
                                            ) {
                                                mainScope.launch {
                                                    allCommentsForUser =
                                                        getAllCommentsForUser(props.serverConfig.getFullHttpURL(),
                                                            comment.user.fullName())
                                                }
                                            } else if (comment.user == allCommentsForUser[1].user) {
                                                allCommentsForUser = emptyList()
                                            }
                                        }
                                    }
                                }
                                TableCell { +comment.item.toString() }
                                TableCell {
                                    ValidationActions {
                                        inputComment = comment
                                        commentValidated = { action:ActionType ->
                                            validateComment(comment, action, webSocket) { comment ->
                                                updateCommentsList.invoke { prevList -> prevList.filterNot { it == comment } }
                                            }
                                        }
                                    }
                                }
                                TableCell { em { +comment.fullComment } }
                            }
                        }
                    }
                }
            }
        }
        if (!allCommentsForUser.isEmpty()) {
            Stack {
                Stack {
                    direction = responsive (StackDirection.row)
                    sx {
                        justifyContent = JustifyContent.spaceBetween
                    }
                    Typography{ + allCommentsForUser[0].user.fullName()}
                    HighlightOffOutlined {
                        onClick = { _ -> allCommentsForUser = emptyList()}
                    }
                }
                direction = responsive (StackDirection.column)
                allCommentsForUser.map { comment ->
                    Card {
                        CardContent {
                            Typography {
                                asDynamic().color = "text.secondary"
                                gutterBottom = true
                                +comment.timestamp.toTimeStr()
                            }
                            Typography {
                                +comment.fullComment
                            }
                        }

                    }
                }
            }
        }
    }
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

fun Instant.toTimeStr():String {
    fun Int.onTD() = toString().asDynamic().padStart(2,'0')
    val time = toLocalDateTime(TimeZone.currentSystemDefault())
    return "${time.hour.onTD()}:${time.minute.onTD()}:${time.second.onTD()}"
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

suspend fun getAllCommentsForUser(url: String, userName: String): List<Comment> {
    val response = window
        .fetch("$url/live/comments/archives/$userName")
        .await()
    val textResponse = response
        .text()
        .await()
    val allComments: List<Comment> = Json.decodeFromString(textResponse)
    return allComments
        .groupBy (Comment::timestamp)
        .values
        .map { l -> l[0] }
        .sortedBy  (Comment::timestamp)
}
