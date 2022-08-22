package be.aufildemescoutures.frontend.controls

import be.aufildemescoutures.domain.core.LiveEvent.LiveControl
import be.aufildemescoutures.frontend.ServerConfig
import kotlinx.browser.window
import react.FC
import react.Props
import kotlinx.coroutines.*
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDateTime
import org.w3c.fetch.RequestInit
import react.dom.html.InputType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.span
import react.dom.html.ReactHTML.ul
import react.useEffectOnce
import react.useState
import kotlin.js.Date
import kotlin.js.json

external interface ControlCenterProps : Props {
    var serverConfig : ServerConfig
    var serverStatus: ServerStatus
    var updateStatus: (ServerStatus) -> Unit
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
    var liveControl:LiveControl by useState<LiveControl>(LiveControl())

    useEffectOnce {
        mainScope.launch {
            props.updateStatus(getLiveStatus(props.serverConfig.getFullHttpURL(),props.serverStatus))
        }
    }
    div {
        span { +props.serverStatus.text}
        ControlCenterInput{
            label = "Live Id"
            defaultValue = liveControl.liveId
            active = (props.serverStatus.status == ServerStatusEnum.NO_LIVE)
            onValueChanged = {liveControl = liveControl.copy(liveId = it); }
        }
        ControlCenterInput{
            label = "Facebook Video ID"
            defaultValue = liveControl.fbVideoId
            active = (props.serverStatus.status == ServerStatusEnum.NO_LIVE)
            onValueChanged = {liveControl = liveControl.copy(fbVideoId = it); }
        }
        ControlCenterInput{
            label = "Taille Inventaire"
            defaultValue = liveControl.providedInventorySize.toString()
            specificInputType = InputType.number
            active = (props.serverStatus.status == ServerStatusEnum.NO_LIVE)
            onValueChanged = {liveControl = liveControl.copy(providedInventorySize = it.toInt()); }
        }
        button {
            +("${ if(props.serverStatus.status == ServerStatusEnum.LIVE) "Stop" else "Start"} live")
            disabled = (!props.serverStatus.connectionActive())
            onClick = { _ ->
                mainScope.launch {
                    props.updateStatus(toggleLive(props.serverConfig.getFullHttpURL(), props.serverStatus,liveControl))
                }
            }
        }
        ul {
            props.serverStatus.logs.map {
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

suspend fun toggleLive(url: String, serverStatus: ServerStatus, liveControl: LiveControl): ServerStatus {
    var body = ""
    var method = "DELETE"
    var message = "Request to stop live"
    if (serverStatus.status == ServerStatusEnum.NO_LIVE) {
        body = "video=${liveControl.fbVideoId}&liveId=${liveControl.liveId}&items=${liveControl.providedInventorySize}"
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

