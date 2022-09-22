package be.aufildemescoutures.frontend

import be.aufildemescoutures.frontend.controls.ControlCenter
import be.aufildemescoutures.frontend.controls.ServerStatus
import be.aufildemescoutures.frontend.controls.ServerStatusEnum
import be.aufildemescoutures.frontend.validation.CommentsToValidate
import kotlinx.browser.document
import mui.material.Stack
import mui.material.StackDirection
import mui.material.styles.ThemeOptions
import mui.material.styles.ThemeProvider
import mui.material.styles.createTheme
import mui.system.responsive
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.useState

external interface AppProps : Props {
    var serverConfig: ServerConfig
}

val lightTheme = createTheme(
    options = js(" { palette:{ primary:{ main: '#1760a5', light: 'skyblue' }, secondary:{ main: '#15c630', }, otherColor:{ main:'#999' } } } ") as? ThemeOptions
)


private val App = FC<AppProps> { props ->
    var serverStatusState by useState(ServerStatus(ServerStatusEnum.FETCHING, "Checking server status"))
    var currentTheme by useState(lightTheme)

    ThemeProvider{
        theme = currentTheme

        Stack {
            direction = responsive(StackDirection.column)
            ControlCenter {
                serverConfig = props.serverConfig
                serverStatus = serverStatusState
                updateStatus = { serverStatusState = it }
            }
            CommentsToValidate {
                serverConfig = props.serverConfig
                serverStatus = serverStatusState
            }
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
