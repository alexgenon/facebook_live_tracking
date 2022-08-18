package be.aufildemescoutures.frontend.validation

import CommentComponent
import be.aufildemescoutures.domain.ActionType
import be.aufildemescoutures.domain.Comment
import be.aufildemescoutures.domain.CommentList
import be.aufildemescoutures.domain.live_tracking.LiveEvent
import be.aufildemescoutures.frontend.ServerConfig
import be.aufildemescoutures.frontend.controls.ServerStatus
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.dom.WebSocket
import react.*
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2


external interface CommentsToValidateProps : Props {
    var serverConfig: ServerConfig
    var serverStatus: ServerStatus
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

fun getCommentsPendingValidationJS(serverConfig: ServerConfig, commentsList: MutableList<Comment>,triggerUpdate: (List<Comment>) -> Unit): WebSocket {
    console.log("${Clock.System.now()} : call to getCommentsPendingValidationJS")
    val ws = WebSocket(serverConfig.getFullWSURL()+"/live/comments/validation")
    ws.onmessage = { message ->
        console.log("${Clock.System.now()} : New message ${message.data as String}")
        val receivedEvents = Json.decodeFromString<List<LiveEvent>>(message.data as String)
        commentsList.addAll(receivedEvents.map {it.comment})
        triggerUpdate(commentsList)
    }
    return ws
}

fun validateComment(comment : Comment, updatedAction: ActionType, commentsList: MutableList<Comment>, ws:WebSocket?,triggerUpdate: (List<Comment>) -> Unit) {
    ws?.send(Json.encodeToString(LiveEvent(comment.copy(action=updatedAction),LiveEvent.commentValidated)))
    if(ws == null){
        console.warn("No Websocket, cannot send update")
    }
    if(!commentsList.remove(comment)){
        console.warn("Cannot remove comment $comment from list")
    }
    triggerUpdate(commentsList)
}

val CommentsToValidate = FC<CommentsToValidateProps> { props ->
    var commentsList:MutableList<Comment> by useState(mutableListOf())
    var commentsListImm by useState(emptyList<Comment>())
    console.log("${Clock.System.now()} : Rendering CommentsToValidate")
    // dirty hack to trigger rerendering because of state update.
    // Adding an element to the MutableList above does not cause state update (commentsList is not assigned a new value)
    // useReducer could be an alternative but it's getting late
    var commentsCount:Int by useState(0)
    var commentSelected:Comment? by useState(null)
    var webSocket: WebSocket? by useState(null)

    useEffectOnce {
        webSocket=getCommentsPendingValidationJS(props.serverConfig,commentsList){ commentsList ->
                commentsCount=commentsList.size
                commentsListImm = commentsListImm + commentsList
        }
    }
    div {
        h2 {+ "${commentsListImm.size} commentaires à revoir"}
        ReactHTML.main {
            commentsListImm.map { it ->
                CommentComponent{
                    comment = it
                    commentValidated = { comment, action -> validateComment(comment,action,commentsList,webSocket) {commentsList -> commentsCount=commentsList.size}}
                    isSelected = (commentSelected == it)
                    selectComment = {  commentSelected = it}
                }
            }
        }
    }
}
