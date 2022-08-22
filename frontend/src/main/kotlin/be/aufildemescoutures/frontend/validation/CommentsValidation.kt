package be.aufildemescoutures.frontend.validation

import CommentComponent
import be.aufildemescoutures.domain.core.ActionType
import be.aufildemescoutures.domain.core.Comment
import be.aufildemescoutures.domain.live_tracking.LiveEvent
import be.aufildemescoutures.frontend.ServerConfig
import be.aufildemescoutures.frontend.controls.ServerStatus
import be.aufildemescoutures.frontend.controls.mainScope
import csstype.*
import emotion.react.css
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.dom.WebSocket
import react.*
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.article
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.span


external interface CommentsToValidateProps : Props {
    var serverConfig: ServerConfig
    var serverStatus: ServerStatus
}

val CommentsToValidate = FC<CommentsToValidateProps> { props ->
    val (commentsList,updateCommentsList) = useState(emptyList<Comment>())
    var commentSelected:Comment? by useState(null)
    var webSocket: WebSocket? by useState(null)
    var allCommentsForUser : List<Comment> by useState(emptyList())

    useEffectOnce {
        webSocket=getCommentsPendingValidationJS(props.serverConfig){ commentsList ->
                updateCommentsList.invoke { prevList -> (prevList + commentsList) }
        }
    }
    h2 { +"${commentsList.size} commentaires à revoir" }
    div {
        css {
            display = Display.flex
            flexDirection = FlexDirection.row
        }
        div {
            css {
                flexGrow = 6 as FlexGrow
            }
            ReactHTML.main {
                commentsList.map { it ->
                    CommentComponent {
                        comment = it
                        commentValidated = { comment, action ->
                            validateComment(comment, action, webSocket) { comment ->
                                updateCommentsList.invoke { prevList -> prevList.filterNot { it == comment } }
                            }
                        }
                        isSelected = (commentSelected == it)
                        selectComment = { commentSelected = it }
                        displayAllCommentsForUser = { comment ->
                            if(allCommentsForUser.isEmpty()
                                || comment.user != allCommentsForUser[1].user ) {
                                mainScope.launch {
                                    allCommentsForUser = getAllCommentsForUser(props.serverConfig.getFullHttpURL(),
                                        comment.user.fullName())
                                }
                            }
                        }
                    }
                }
            }
        }
        if(allCommentsForUser.isNotEmpty() ) {
            div {
                css {
                    flexGrow = 4 as FlexGrow
                }
                div {
                    css {
                        display= Display.flex
                        flexDirection = FlexDirection.row
                        justifyContent = JustifyContent.spaceBetween
                    }
                    h3 {
                        +(allCommentsForUser[0].user.fullName())
                    }
                    span {
                        +"X"
                        onClick = {_ ->
                            allCommentsForUser = emptyList()
                        }
                        css { cursor = Cursor.pointer}
                    }
                }
                ReactHTML.main {
                    allCommentsForUser.map { it ->
                        article{
                            key = "allCommentsForUser-${it.id}"
                            css {
                                display = Display.flex
                                flexDirection = FlexDirection.column
                            }
                            span { +it.timestamp.toString()}
                            span { +it.fullComment}
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

fun getCommentsPendingValidationJS(serverConfig: ServerConfig, triggerUpdate: (List<Comment>) -> Unit): WebSocket {
    console.log("${Clock.System.now()} : call to getCommentsPendingValidationJS")
    val ws = WebSocket(serverConfig.getFullWSURL()+"/live/comments/validation")
    ws.onmessage = { message ->
        val receivedEvents = Json.decodeFromString<List<LiveEvent>>(message.data as String)
        triggerUpdate(receivedEvents.map { it.comment })
    }
    return ws
}

fun validateComment(comment : Comment, updatedAction: ActionType, ws:WebSocket?, triggerRemoval: (Comment) -> Unit) {
    ws?.send(Json.encodeToString(LiveEvent(comment.copy(action=updatedAction),LiveEvent.commentValidated)))
    if(ws == null){
        console.warn("No Websocket, cannot send update")
    }
    triggerRemoval(comment)
}

suspend fun getAllCommentsForUser(url: String,userName:String): List<Comment> {
    val response = window
        .fetch("$url/live/comments/archives/$userName")
        .await()
    val textResponse= response
        .text()
        .await()
    return Json.decodeFromString(textResponse)
}
