package be.aufildemescoutures.frontend.validation

import be.aufildemescoutures.domain.live_tracking.core.comment.CommentForContest
import be.aufildemescoutures.frontend.util.toTimeStr
import csstype.Color
import mui.material.ListItem
import mui.material.Typography
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props

external interface ContestValidationProps: Props {
    var comments: List<CommentForContest>
}

val ContestValidation = FC<ContestValidationProps> { props ->
    Typography{
        variant = TypographyVariant.h2
        +"Mode concours : ${props.comments.size} proposition"
    }
    mui.material.List {
        props.comments.map { c->
            ListItem {
                sx {
                    if(c.isAMatch) {
                        backgroundColor = Color("#b9c78e")
                    }
                }
                +"${c.comment.timestamp.toTimeStr()} - ${c.comment.user.fullName()}: ${c.comment.fullComment}"
            }
        }
    }
}
