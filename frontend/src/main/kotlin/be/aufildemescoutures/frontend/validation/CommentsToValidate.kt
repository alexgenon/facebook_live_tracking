import be.aufildemescoutures.domain.live_tracking.core.comment.ActionType
import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import be.aufildemescoutures.frontend.ServerConfig
import be.aufildemescoutures.frontend.controls.eventToInputValue
import be.aufildemescoutures.frontend.controls.mainScope
import be.aufildemescoutures.frontend.util.toTimeStr
import be.aufildemescoutures.frontend.validation.ValidationActions
import csstype.*
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import mui.icons.material.HighlightOffOutlined
import mui.icons.material.UpdateOutlined
import mui.material.*
import mui.material.Size
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.*
import react.dom.html.InputType
import react.dom.html.ReactHTML
import react.dom.onChange

external interface CommentsToValidateProps : Props {
    var serverConfig: ServerConfig
    var comments: List<Comment>
    var commentValidated: (Comment, ActionType) -> Unit
}

val CommentsToValidate = FC<CommentsToValidateProps> { props ->
    var allCommentsForUser: List<Comment> by useState(emptyList())
    var bulkValidationCount: Int by useState(30)
    var selectedComments: List<Comment> by useState(emptyList())

    val commentsList = props.comments
    val commentValidated = props.commentValidated

    Typography {
        variant = TypographyVariant.h2
        +"${commentsList.size} commentaires à revoir"
    }

    Stack {
        direction = responsive(StackDirection.row)
        spacing = responsive(2)
        sx {
            justifyContent = JustifyContent.spaceBetween
            padding = 3.em
        }
        Stack {
            direction = responsive(StackDirection.column)
            spacing = responsive(2)
            sx {
                if(allCommentsForUser.isEmpty()) {
                    width = 100.pct
                }else {
                    width = 60.pct
                }
            }
            Box {
                Button {
                    +"Valider $bulkValidationCount commentaires"
                    onClick = { _ ->
                        commentsList
                            .take(bulkValidationCount)
                            .forEach { commentValidated(it, it.action) }
                    }
                }
                TextField {
                    label = ReactNode("Quantité validation en masse")
                    variant = FormControlVariant.outlined
                    defaultValue = bulkValidationCount.toString()
                    type = InputType.number
                    onChange =
                        { bulkValidationCount = eventToInputValue(it).toInt() }
                }
            }
            TableContainer {
                component = Paper.create().type

                Table {
                    size = Size.small
                    TableHead {
                        TableRow {
                            TableCell {
                                sx { width = 13.em }
                                +"Action"
                            }
                            TableCell {
                                sx { width = 3.ch }
                                +"Nb"
                            }
                            TableCell {
                                sx { width = 35.ch }
                                +"Nom"
                            }
                            TableCell {
                                +"Commentaire"
                            }

                            TableCell {
                                sx { width = 2.em }
                                +"Hist."
                            }
                        }
                    }
                    TableBody {
                        commentsList.map { comment ->
                            TableRow {
                                key = comment.id.toString()

                                sx {
                                    if (selectedComments.contains(comment)) {
                                        backgroundColor = Color("aliceblue")
                                    }
                                    hover {
                                        backgroundColor = Color("lightblue")
                                    }
                                }

                                TableCell {
                                    ValidationActions {
                                        inputComment = comment
                                        actionForComment = { action: ActionType -> commentValidated(comment, action) }
                                    }
                                }
                                TableCell { +comment.item.toString() }
                                TableCell {
                                    +comment.user.fullName()
                                    onClick = { _ ->
                                        window.navigator.clipboard.writeText(comment.user.fullName())
                                        selectedComments = listOf(comment)
                                    }
                                    sx {
                                        maxWidth = 35.ch
                                        whiteSpace = WhiteSpace.nowrap
                                        overflow = Overflow.hidden
                                    }
                                }
                                TableCell { ReactHTML.em { +comment.fullComment } }
                                TableCell {
                                    UpdateOutlined {
                                        onClick = { _ ->
                                            if (allCommentsForUser.isEmpty()
                                                || comment.user != allCommentsForUser[0].user
                                            ) {
                                                mainScope.launch {
                                                    allCommentsForUser =
                                                        getAllCommentsForUser(props.serverConfig.getFullHttpURL(),
                                                            comment.user.fullName())
                                                }
                                            } else if (comment.user == allCommentsForUser[1].user) {
                                                allCommentsForUser = emptyList()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!allCommentsForUser.isEmpty()) {
            Stack {
                sx {
                    width=40.pct
                }
                Stack { // Heading Stack
                    direction = responsive(StackDirection.row)
                    sx {
                        justifyContent = JustifyContent.spaceBetween
                    }
                    Typography { +allCommentsForUser[0].user.fullName() }
                    HighlightOffOutlined {
                        onClick = { _ -> allCommentsForUser = emptyList() }
                    }
                }
                direction = responsive(StackDirection.column)
                allCommentsForUser.map { comment ->
                    Card {
                        CardContent {
                            Typography {
                                asDynamic().color = "text.secondary"
                                gutterBottom = true
                                +comment.timestamp.toTimeStr()
                            }
                            Typography {
                                +comment.fullComment
                            }
                        }
                    }
                }
            }
        }
    }
}

suspend fun getAllCommentsForUser(url: String, userName: String): List<Comment> {
    val response = window
        .fetch("$url/live/comments/archives/$userName")
        .await()
    val textResponse = response
        .text()
        .await()
    val allComments: List<Comment> = Json.decodeFromString(textResponse)
    return allComments
        .groupBy(Comment::timestamp)
        .values
        .map { l -> l[0] }
        .sortedBy(Comment::timestamp)
}
