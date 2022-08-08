package be.aufildemescoutures.frontend

import be.aufildemescoutures.frontend.controls.ControlCenter
import kotlinx.browser.document
import react.create
import react.dom.client.createRoot

fun main() {
    val container = document.createElement("div")
    document.body!!.appendChild(container)

    val controlCenter = ControlCenter.create{
        serverConfig=ServerConfig(serverUrl = "localhost:8080",
            httpProtocol="http://",
            wsProtocol="ws://")
    }
    createRoot(container).render(controlCenter)
}
