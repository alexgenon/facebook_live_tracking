package be.aufildemescoutures.frontend.controls

import be.aufildemescoutures.frontend.ServerConfig
import kotlinx.browser.window
import react.FC
import react.Props
import kotlinx.coroutines.*
import org.w3c.fetch.RequestInit
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul
import react.useEffectOnce
import react.useState
import kotlin.js.Date
import kotlin.js.json

external interface ControlCenterProps : Props {
    var serverConfig : ServerConfig
}

data class ServerStatus(val status: ServerStatusEnum, val text: String, val logs: List<String> = emptyList()) {
    fun connectionActive(): Boolean = (status != ServerStatusEnum.FETCHING)
    fun addLog(text:String) = this.copy(logs = logs + ("${timeAsString()}: $text"))
    private fun timeAsString():String  {
        val now = Date()
        return "${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}"
    }
}

enum class ServerStatusEnum { FETCHING, LIVE, NO_LIVE }

val mainScope = MainScope()

val ControlCenter = FC<ControlCenterProps> { props ->

    var serverStatus by useState(ServerStatus(ServerStatusEnum.FETCHING, "Checking server status"))
    useEffectOnce {
        mainScope.launch {
            serverStatus = getLiveStatus(props.serverConfig.getFullHttpURL(),serverStatus)
        }
    }
    div {

        button {
            +((if (serverStatus.status == ServerStatusEnum.LIVE) "Stop " else "") + serverStatus.text)
            disabled = (!serverStatus.connectionActive())
            onClick = { _ ->
                mainScope.launch {
                    serverStatus = toggleLive(props.serverConfig.getFullHttpURL(), serverStatus)
                }
            }
        }
        ul {
            serverStatus.logs.map {
                li {
                    +it
                }
            }
        }
    }
}

const val LIVE_STATUS_ENDPOINT: String = "/live"
suspend fun getLiveStatus(url: String,serverStatus: ServerStatus): ServerStatus {
    val response = window
        .fetch(url + LIVE_STATUS_ENDPOINT)
        .await()
    return if (response.status == 404.toShort()) {
        val responseText = "No Live started"
        ServerStatus(ServerStatusEnum.NO_LIVE, responseText,serverStatus.logs)
            .addLog(responseText)
    } else {
        val responseText = response.text().await()
        ServerStatus(ServerStatusEnum.LIVE, responseText,serverStatus.logs)
            .addLog(responseText)
    }
}

suspend fun toggleLive(url: String, serverStatus: ServerStatus): ServerStatus {
    var body = ""
    var method = "DELETE"
    var message = "Request to stop live"
    if (serverStatus.status == ServerStatusEnum.NO_LIVE) {
        body = "video=fb-123&liveId=Live-2022-07-17&items=100"
        method = "POST"
        message = "Request to start live"
    }
    val toggleResponse= window.fetch(input = url + LIVE_STATUS_ENDPOINT ,
        init = RequestInit(
            method = method,
            body = body,
            headers = json("Content-Type" to "application/x-www-form-urlencoded")
        ))
        .await()
    return getLiveStatus(url, serverStatus.addLog("$message result ${toggleResponse.status}"))
}
