package be.aufildemescoutures.frontend.controls

import react.FC
import react.Props
import react.dom.html.InputType
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div

external interface ControlCenterInputProps : Props {
    var label: String
    var defaultValue: String
    var active: Boolean
    var specificInputType: InputType?
    var onValueChanged: (String) -> Unit
}

val ControlCenterInput = FC<ControlCenterInputProps> { props ->
    div {
        ReactHTML.label {
            htmlFor = props.label
            +props.label
        }
        ReactHTML.input {
            id = props.label
            type = props.specificInputType?:InputType.text
            disabled = !props.active
            defaultValue = props.defaultValue
            onChange = {ev -> props.onValueChanged(ev.target.value)}
        }
    }
}
