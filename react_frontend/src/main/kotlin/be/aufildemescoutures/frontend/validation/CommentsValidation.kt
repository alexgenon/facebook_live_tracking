package be.aufildemescoutures.frontend.validation

import be.aufildemescoutures.frontend.ServerConfig
import be.aufildemescoutures.frontend.controls.ServerStatus
import be.aufildemescoutures.frontend.controls.ServerStatusEnum
import be.aufildemescoutures.frontend.controls.mainScope
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.launch
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.useState

external interface CommentsToValidateProps : Props {
    var serverConfig: ServerConfig
    var serverStatus: ServerStatus
}

suspend fun getCommentsPendingValidation(serverConfig: ServerConfig):String{
    val client = HttpClient()
    val response = client.request("${serverConfig.getFullHttpURL()}/live/comments/validation/list")

    return "Here you should see live comments : ${response.body<String>()}"
}

val CommentsToValidate = FC<CommentsToValidateProps> { props ->
    var commentsList : String by useState<String>("")
    if(props.serverStatus.status == ServerStatusEnum.LIVE){
        mainScope.launch {
            commentsList = getCommentsPendingValidation(props.serverConfig)
        }
        div {+commentsList }
        div { +"coucou gamin!"}
    } else {
        div {
            +("No live running")
        }
    }
}
