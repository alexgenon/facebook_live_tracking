package be.aufildemescoutures.frontend.validation

import be.aufildemescoutures.domain.live_tracking.core.comment.CommentForContest
import mui.material.Typography
import mui.material.styles.TypographyVariant
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
}
