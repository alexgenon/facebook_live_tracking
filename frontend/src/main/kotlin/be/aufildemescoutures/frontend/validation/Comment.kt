import be.aufildemescoutures.domain.core.ActionType
import be.aufildemescoutures.domain.core.Comment
import csstype.*
import emotion.react.css
import kotlinx.browser.window
import react.FC
import react.Props
import react.dom.html.ReactHTML.article
import react.dom.html.ReactHTML.em
import react.dom.html.ReactHTML.span
import react.useState


external interface CommentProps : Props {
    var comment: Comment
    var commentValidated: (Comment, ActionType) -> Unit
    var isSelected: Boolean
    var selectComment : () -> Unit
    var displayAllCommentsForUser: (Comment) -> Unit
}


val CommentComponent = FC<CommentProps> { props ->
    val comment = props.comment
    var actionsExpanded by useState(false)
    article {
        key=comment.commentId
        css {
            display = Display.flex
            alignItems = AlignItems.center
            gap = 12.px
            padding = Padding(10.px, 30.px, 10.px, 10.px)
            overflow = Overflow.hidden
            borderRadius = 10.px
            boxShadow = BoxShadow(0.px, 5.px, 7.px, (-1).px, rgba(51, 51, 51, 0.23))
                hover {
                    boxShadow = BoxShadow(0.px, 9.px, 47.px, (11).px, rgba(51, 51, 51, 0.18))
                }
            if(props.isSelected){
                backgroundColor = Color("#daebeb")
            }
        }
        span {
            css {
                color = Color("#979cb0")
                fontWeight = FontWeight.bold
                fontSize = 20.px
                letterSpacing = 0.64f.px
                cursor = Cursor.pointer
                paddingLeft = 5.px
                paddingRight = 5.px
                borderRadius = 8.px
                hover {
                    backgroundColor = Color("#b9c78e")
                }
            }
            onClick = { _ ->
                props.selectComment()
                window.navigator.clipboard.writeText(comment.user.fullName())
            }
            +comment.user.fullName()
        }
        span {
            +">"
            css {
                cursor = Cursor.pointer
            }
            onClick = {_ ->
                props.displayAllCommentsForUser(comment)
            }
        }
        span {
            css {
                cursor = Cursor.pointer

            }
            onMouseEnter = { actionsExpanded = true; }
            onMouseLeave = { actionsExpanded = false; }

            CommentAction{
                actionType = comment.action
                actionSelected = { props.commentValidated(comment,comment.action)}
            }

            if (actionsExpanded) {
                ActionType.values()
                    .filterNot { it == comment.action }
                    .map { action ->
                        CommentAction{
                            actionType = action
                            actionSelected = { props.commentValidated(comment,action)}
                        }
                    }
            }
        }
        span { +comment.item.toString() }
        span { em { +comment.fullComment } }
    }
}


