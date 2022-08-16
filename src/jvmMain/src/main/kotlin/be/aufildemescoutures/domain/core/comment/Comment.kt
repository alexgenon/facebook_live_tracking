package be.aufildemescoutures.domain

import be.aufildemescoutures.domain.core.customer.Customer
import kotlinx.datetime.Instant

@kotlinx.serialization.Serializable
data class Comment(
    val commentId: String,
    val user: FacebookUser,
    val item: Int,
    val timestamp:  Instant,
    val fullComment: String,
    val action: ActionType
){
    fun username(): String = user.name

    val id = CommentId(commentId+"_"+item)
}

@kotlinx.serialization.Serializable
data class FacebookUser(val name: String, val id: String): Customer {
    override fun fullName() =name
    companion object {
        @JvmStatic
        val NoRecordedUser = FacebookUser("<No user>", "<No user>")
    }
}
enum class ActionType { BUY, REVIEW, QUESTION, NOTHING }
typealias CommentList = List<Comment>

@JvmInline
@kotlinx.serialization.Serializable
value class CommentId(val id: String)
