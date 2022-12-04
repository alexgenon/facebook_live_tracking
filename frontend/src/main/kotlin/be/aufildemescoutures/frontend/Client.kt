package be.aufildemescoutures.frontend

import be.aufildemescoutures.frontend.controls.ControlCenter
import be.aufildemescoutures.frontend.controls.mainScope
import be.aufildemescoutures.frontend.validation.CommentsValidation
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import mui.material.Stack
import mui.material.StackDirection
import mui.material.styles.ThemeOptions
import mui.material.styles.ThemeProvider
import mui.material.styles.createTheme
import mui.system.responsive
import org.w3c.fetch.RequestInit
import react.*
import react.dom.client.createRoot
import kotlin.js.json

external interface AppProps : Props {
    var serverConfig: ServerConfig
}

val lightTheme = createTheme(
    options = js(" { palette:{ primary:{ main: '#1760a5', light: 'skyblue' }, secondary:{ main: '#15c630', }, otherColor:{ main:'#999' } } } ") as? ThemeOptions
)


private val App = FC<AppProps> { props ->
    val (serverStatusState, updateServerStatusState) = useState(ServerStatus(ServerStatusEnum.FETCHING,
        "Checking server status"))
    val currentTheme by useState(lightTheme)

    useEffectOnce {
        getLiveStatus(props.serverConfig.getFullHttpURL(), updateServerStatusState)
    }

    ThemeProvider {
        theme = currentTheme

        Stack {
            direction = responsive(StackDirection.column)
            ControlCenter {
                serverConfig = props.serverConfig
                serverStatus = serverStatusState
                toggleLiveCallback = { liveId, fbVideoId, inventorySize ->
                    toggleLive(serverConfig.getFullHttpURL(), serverStatusState,
                        liveId, fbVideoId, inventorySize, updateServerStatusState)
                }
                toggleContestCallback = { contestKeyword ->
                    toggleContest(props.serverConfig.getFullHttpURL(), serverStatus, contestKeyword,updateServerStatusState)
                }
            }
            CommentsValidation {
                serverConfig = props.serverConfig
                serverStatus = serverStatusState
                contestSwitch = { contest -> updateServerStatusState.invoke { old -> old.contestStarted(contest) } }
            }
        }
    }
}

fun main() {
    val container = document.createElement("div")
    document.body!!.appendChild(container)
    val effectiveServerConfig = defineServerConfig()

    val app = App.create {
        serverConfig = effectiveServerConfig
    }
    createRoot(container).render(app)
}

private fun defineServerConfig(): ServerConfig {
    val webPackMode = js("ENV_WEBPACK_MODE")
    console.log("Webpack mode is $webPackMode")
    return if ("production".equals(webPackMode as? String, ignoreCase = true)) {
        ServerConfig(serverUrl = window.location.hostname,
            port = window.location.port,
            httpProtocol = window.location.protocol + "//",
            wsProtocol = "ws://")
    } else {
        ServerConfig(serverUrl = "localhost",
            port = "8080",
            httpProtocol = "http://",
            wsProtocol = "ws://"
        )
    }
}

const val LIVE_STATUS_ENDPOINT: String = "/live"
fun getLiveStatus(url: String, updateServerStatus: StateSetter<ServerStatus>) {
    mainScope.launch {
        console.log(url)
        val response = window
            .fetch(url + LIVE_STATUS_ENDPOINT)
            .await()
        if (response.status == 404.toShort()) {
            val responseText = "No Live started"
            updateServerStatus { oldStatus ->
                oldStatus.copy(status = ServerStatusEnum.NO_LIVE, text = responseText)
                    .addLog(responseText)
            }
        } else {
            val responseText = response.text().await()
            // TODO : get the liveControl data from the rest endpoint including the contest mode
            updateServerStatus {
                it.copy(status = ServerStatusEnum.LIVE, text = responseText)
                    .addLog(responseText)
            }
        }
    }
}

fun toggleLive(
    url: String,
    serverStatus: ServerStatus,
    liveId: String,
    fbVideoId: String,
    providedInventorySize: Int,
    updateServerStatus: StateSetter<ServerStatus>,
) {
    mainScope.launch {
        var body = ""
        var method = "DELETE"
        var message = "Request to stop live"
        if (serverStatus.status == ServerStatusEnum.NO_LIVE) {
            body = "video=${fbVideoId}&liveId=${liveId}&items=${providedInventorySize}"
            method = "POST"
            message = "Request to start live video=${fbVideoId}&liveId=${liveId}&items=${providedInventorySize}"
        }
        val toggleResponse = window.fetch(
            input = url + LIVE_STATUS_ENDPOINT,
            init = RequestInit(
                method = method,
                body = body,
                headers = json("Content-Type" to "application/x-www-form-urlencoded")
            ))
            .await()
        updateServerStatus { it.addLog("$message result ${toggleResponse.status}") }
        getLiveStatus(url,updateServerStatus)
    }
}

const val CONTEST_ENDPOINT = "/live/comments/validation/contest/"
fun toggleContest(
    url: String,
    serverStatus: ServerStatus,
    providedContestKeyword: String,
    updateServerStatus: StateSetter<ServerStatus>,
) {
    mainScope.launch {
        // Assume first we have a contest ongoing that will be stopped
        val method: String
        var fullInputURL = url + CONTEST_ENDPOINT
        val logText: String

        if (serverStatus.contestOngoing()) {
            method = "DELETE"
            logText = "Stopping contest"
        } else { // No contest ==> Start one with keyword from liveControl
            method = "POST"
            fullInputURL += "?keyword=${providedContestKeyword}"
            logText = "Contest for $providedContestKeyword started"
        }

        val toggleResponse = window.fetch(
            input = fullInputURL,
            init = RequestInit(method = method)).await()
        updateServerStatus { it.addLog("$logText: ${toggleResponse.status}") }
    }
}

