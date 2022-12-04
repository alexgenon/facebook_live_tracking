package be.aufildemescoutures.frontend.controls

import be.aufildemescoutures.frontend.ServerConfig
import be.aufildemescoutures.frontend.ServerStatus
import be.aufildemescoutures.frontend.ServerStatusEnum
import csstype.em
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.sx
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.events.FormEvent
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul
import react.dom.onChange

external interface ControlCenterProps : Props {

    var serverConfig: ServerConfig
    var serverStatus: ServerStatus
    var toggleLiveCallback: (String,String,Int) -> Unit
    var toggleContestCallback : (String) -> Unit
}

val mainScope = MainScope()

val ControlCenter = FC<ControlCenterProps> { props ->
    var liveId: String by useState(Clock.System.now().toString())
    var fbVideoId: String by useState("")
    var providedInventorySize: Int by useState(100)
    var providedContestKeyword: String by useState("") // user input

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
                defaultValue = liveId
                disabled = (props.serverStatus.status != ServerStatusEnum.NO_LIVE)
                onChange = { liveId = eventToInputValue(it) }
            }
            TextField {
                label = ReactNode("Facebook Video ID")
                variant = FormControlVariant.outlined
                defaultValue = fbVideoId
                disabled = (props.serverStatus.status != ServerStatusEnum.NO_LIVE)
                onChange = { fbVideoId = eventToInputValue(it) }
            }

            Button {
                +("${if (props.serverStatus.status == ServerStatusEnum.LIVE) "Stop" else "Start"} live")
                variant = ButtonVariant.contained
                disabled = (!props.serverStatus.connectionActive())
                onClick = { _ -> props.toggleLiveCallback(liveId,fbVideoId,providedInventorySize) }
            }

            TextField {
                label = ReactNode("Mot clé concours")
                variant = FormControlVariant.outlined
                onChange =
                    { providedContestKeyword = eventToInputValue(it) }
            }
            Button {
                +("${if(props.serverStatus.contestOngoing()) "Arrêter" else "Démarrer"} concours")
                variant = ButtonVariant.contained
                onClick = { _ ->
                    mainScope.launch { props.toggleContestCallback(providedContestKeyword)
                    }
                }
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
    (event.target as? HTMLInputElement)?.value ?: "Error Getting value"


