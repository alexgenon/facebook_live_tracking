package be.aufildemescoutures.frontend

import be.aufildemescoutures.frontend.controls.ControlCenter
import be.aufildemescoutures.frontend.controls.ServerStatus
import be.aufildemescoutures.frontend.controls.ServerStatusEnum
import be.aufildemescoutures.frontend.validation.CommentsToValidate
import kotlinx.browser.document
import kotlinx.datetime.Clock
import react.*
import react.dom.client.createRoot
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1

external interface AppProps : Props {
    var serverConfig: ServerConfig
}

private val App = FC<AppProps> { props ->
    var serverStatusState by useState(ServerStatus(ServerStatusEnum.FETCHING, "Checking server status"))

    div {

        h1 { +"Centre de contrôle" }
        ControlCenter {
            serverConfig = props.serverConfig
            serverStatus = serverStatusState
            updateStatus = { serverStatusState = it }
        }
        h1 { +"Commentaires" }
        CommentsToValidate {
            serverConfig = props.serverConfig
            serverStatus = serverStatusState
        }
    }
}

fun main() {
    val container = document.createElement("div")
    document.body!!.appendChild(container)

    val app = App.create {
        serverConfig = ServerConfig(serverUrl = "localhost",
            port = 8080,
            httpProtocol = "http://",
            wsProtocol = "ws://")
    }
    createRoot(container).render(app)
}
