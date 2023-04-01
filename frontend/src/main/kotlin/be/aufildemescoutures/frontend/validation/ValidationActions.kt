package be.aufildemescoutures.frontend.validation

import be.aufildemescoutures.domain.live_tracking.core.comment.ActionType
import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import csstype.pct
import mui.icons.material.DoneOutline
import mui.material.*
import mui.system.responsive
import mui.system.sx
import react.*

external interface ValidationActionsProps : Props {
    var inputComment: Comment
    var actionForComment: (ActionType) -> Unit
}

val ValidationActions = FC<ValidationActionsProps> { props ->
    val comment = props.inputComment
    var selectedAction by useState(comment.action)

    Stack {
        direction = responsive(StackDirection.row)
        @Suppress("UPPER_BOUND_VIOLATED")
        Autocomplete<AutocompleteProps<ActionType>> {
            options = ActionType.values()
            value = comment.action
            size = Size.small
            getOptionLabel = { it.toString() }
            autoSelect = true
            onChange = { _, actionDyn, _, _ ->
                val action = actionDyn as ActionType
                selectedAction = action
                props.actionForComment(action)
            }
            sx {width=100.pct }
            renderInput = { params ->
                TextField.create {
                    +params
                    variant = FormControlVariant.standard
                }
            }
        }
        IconButton{
            DoneOutline {}
            onClick = { _ -> props.actionForComment(selectedAction)}
        }
    }
}
