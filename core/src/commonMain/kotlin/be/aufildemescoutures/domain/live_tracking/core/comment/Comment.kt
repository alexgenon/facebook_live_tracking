package be.aufildemescoutures.domain.live_tracking.core.comment

import be.aufildemescoutures.domain.live_tracking.core.customer.Customer
import kotlinx.datetime.Instant
import kotlin.jvm.JvmInline

@kotlinx.serialization.Serializable
data class Comment(
    val commentId: String,
    val user: Customer,
    val item: Int,
    val timestamp:  Instant,
    val fullComment: String,
    val action: ActionType
){
    val id = CommentId(commentId+"_"+item)
}

enum class ActionType { BUY, REVIEW, QUESTION, NOTHING, CONTEST }

@JvmInline
@kotlinx.serialization.Serializable
value class CommentId(val id: String)

