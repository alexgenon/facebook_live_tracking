package be.aufildemescoutures.frontend.validation

import be.aufildemescoutures.domain.live_tracking.core.comment.ActionType
import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import csstype.Cursor
import csstype.FlexDirection
import mui.material.Chip
import mui.material.FormControl
import mui.material.FormControlMargin
import mui.material.Size
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.useState

external interface ValidationActionsProps : Props {
    var inputComment: Comment
    var actionForComment: (ActionType) -> Unit
}

val ValidationActions = FC<ValidationActionsProps> { props ->
    val comment = props.inputComment
    var expanded: Boolean by useState(false)
    val listOfActions: List<ActionType> = listOf(comment.action) +
            if (expanded) {
                ActionType.values().filterNot { it == comment.action }
            } else {
                emptyList()
            }

    FormControl {
        listOfActions.map { action ->
            Chip {
                label = ReactNode(action.toString())
                onClick = { _ -> props.actionForComment(action) }
            }
        }
        onMouseOver = { _ -> expanded = true }
        onMouseLeave = { _ -> expanded = false }
        margin = FormControlMargin.dense
        size = Size.small
        sx {
            flexDirection = FlexDirection.row
            cursor = Cursor.pointer
        }
    }
}
