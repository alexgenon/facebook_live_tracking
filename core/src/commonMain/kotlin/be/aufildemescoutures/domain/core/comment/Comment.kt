package be.aufildemescoutures.domain.core

import be.aufildemescoutures.domain.core.customer.Customer
import be.aufildemescoutures.domain.core.customer.CustomerId
import kotlinx.datetime.Instant
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

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

enum class ActionType { BUY, REVIEW, QUESTION, NOTHING }

@JvmInline
@kotlinx.serialization.Serializable
value class CommentId(val id: String)

