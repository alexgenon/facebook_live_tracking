import be.aufildemescoutures.domain.ActionType
import csstype.Color
import csstype.px
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.span

external interface CommentActionProps : Props {
    var actionType: ActionType
    var actionSelected: () -> Unit
}

val CommentAction = FC<CommentActionProps>{ props ->
    span {
        +(props.actionType.toString())
        css {
            hover {
                backgroundColor = Color("#b9c78e")
            }
            paddingLeft = 5.px
            paddingRight = 5.px
            borderRadius = 8.px
        }
        onClick = { _ -> props.actionSelected() }
    }
}


