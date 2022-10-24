package be.aufildemescoutures.frontend.controls

import be.aufildemescoutures.domain.live_tracking.core.live_event.LiveControl
import be.aufildemescoutures.frontend.ServerConfig
import csstype.em
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.sx
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import org.w3c.fetch.RequestInit
import react.*
import react.dom.events.FormEvent
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul
import react.dom.onChange
import kotlin.js.Date
import kotlin.js.json

external interface ControlCenterProps : Props {
    var serverConfig: ServerConfig
    var serverStatus: ServerStatus
    var updateStatus: (ServerStatus) -> Unit
}

data class ServerStatus(val status: ServerStatusEnum, val text: String, val logs: List<String> = emptyList()) {
    fun connectionActive(): Boolean = (status != ServerStatusEnum.FETCHING)
    fun addLog(text: String) = this.copy(logs = logs + ("${timeAsString()}: $text"))
    private fun timeAsString(): String {
        val now = Date()
        return "${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}"
    }
}

enum class ServerStatusEnum { FETCHING, LIVE, NO_LIVE,LIVE_CONTEST }

val mainScope = MainScope()

val ControlCenter = FC<ControlCenterProps> { props ->
    var liveControl: LiveControl by useState<LiveControl>(LiveControl())

    useEffectOnce {
        mainScope.launch {
            props.updateStatus(getLiveStatus(props.serverConfig.getFullHttpURL(), props.serverStatus))
        }
    }
    div {
        Typography {
            variant = TypographyVariant.h2
            +"Centre de contrôle"
        }
        Grid {
            sx {
                marginTop = 1.em
                marginBottom = 1.em
                marginLeft = 2.em
            }
            TextField {
                value = props.serverStatus.text
                label = ReactNode("Status")
                disabled = true
            }
            TextField {
                label = ReactNode("Live Id")
                variant = FormControlVariant.outlined
                defaultValue = liveControl.liveId
                disabled = (props.serverStatus.status != ServerStatusEnum.NO_LIVE)
                onChange = { liveControl = liveControl.copy(liveId = eventToInputValue(it)) }
            }
            TextField {
                label = ReactNode("Facebook Video ID")
                variant = FormControlVariant.outlined
                defaultValue = liveControl.fbVideoId
                disabled = (props.serverStatus.status != ServerStatusEnum.NO_LIVE)
                onChange = { liveControl = liveControl.copy(fbVideoId = eventToInputValue(it)); }
            }
            TextField {
                label = ReactNode("Taille Inventaire")
                variant = FormControlVariant.outlined
                defaultValue = liveControl.providedInventorySize.toString()
                type = InputType.number
                disabled = (props.serverStatus.status != ServerStatusEnum.NO_LIVE)
                onChange =
                    { liveControl = liveControl.copy(providedInventorySize = eventToInputValue(it).toInt()); }
            }

            Button {
                +("${if (props.serverStatus.status == ServerStatusEnum.LIVE) "Stop" else "Start"} live")
                variant = ButtonVariant.contained
                disabled = (!props.serverStatus.connectionActive())
                onClick = { _ ->
                    mainScope.launch {
                        props.updateStatus(toggleLive(props.serverConfig.getFullHttpURL(),
                            props.serverStatus,
                            liveControl))
                    }
                }
            }

            Button {
                +"Démarrer concours"
                variant = ButtonVariant.contained
                onClick = { _ ->
                    mainScope.launch {
                        props.updateStatus(toggleContest(props.serverConfig.getFullHttpURL(),
                            props.serverStatus,
                            liveControl))
                    }
                }
            }
            TextField {
                label = ReactNode("Mot clé concours")
                variant = FormControlVariant.outlined
                onChange =
                    { liveControl = liveControl.copy(contestKeyword = eventToInputValue(it)); }
            }
        }
        Accordion {
            sx {
                marginTop = 1.em
                marginBottom = 1.em
                marginLeft = 2.em
            }
            AccordionSummary {
                Typography {
                    +"Journal des évènements"
                    variant = TypographyVariant.h3
                }
            }
            AccordionDetails {
                ul {
                    props.serverStatus.logs.map {
                        li {
                            +it
                        }
                    }
                }
            }
        }
    }
}

fun eventToInputValue(event: FormEvent<HTMLDivElement>) =
    (event.target as? HTMLInputElement)?.value ?: "Error Getting value";

const val LIVE_STATUS_ENDPOINT: String = "/live"
suspend fun getLiveStatus(url: String, serverStatus: ServerStatus): ServerStatus {
    console.log(url)
    val response = window
        .fetch(url + LIVE_STATUS_ENDPOINT)
        .await()
    return if (response.status == 404.toShort()) {
        val responseText = "No Live started"
        ServerStatus(ServerStatusEnum.NO_LIVE, responseText, serverStatus.logs)
            .addLog(responseText)
    } else {
        val responseText = response.text().await()
        ServerStatus(ServerStatusEnum.LIVE, responseText, serverStatus.logs)
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
    val toggleResponse = window.fetch(
        input = url + LIVE_STATUS_ENDPOINT,
        init = RequestInit(
            method = method,
            body = body,
            headers = json("Content-Type" to "application/x-www-form-urlencoded")
        ))
        .await()
    return getLiveStatus(url, serverStatus.addLog("$message result ${toggleResponse.status}"))
}

const val CONTEST_ENDPOINT = "/live/comments/validation/contest/"
suspend fun toggleContest(url: String, serverStatus: ServerStatus, liveControl: LiveControl):ServerStatus {
    val toggleResponse = window.fetch(
        input = url + CONTEST_ENDPOINT+"?keyword=${liveControl.contestKeyword}",
        init = RequestInit(method = "POST")

    ).await()
    return serverStatus.addLog("Contest for ${liveControl.contestKeyword} started: ${toggleResponse.status}")
}


