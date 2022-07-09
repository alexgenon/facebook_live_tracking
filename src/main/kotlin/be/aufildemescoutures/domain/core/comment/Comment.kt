package be.aufildemescoutures.domain

import be.aufildemescoutures.domain.core.customer.Customer
import java.time.LocalDateTime

data class Comment(
    val commentId: String,
    val user: FacebookUser,
    val item: Int,
    val timestamp: LocalDateTime,
    val fullComment: String,
    val action: ActionType
){
    fun username(): String = user.name

    val id = CommentId(commentId+"_"+item)
}

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
value class CommentId(val id: String)
