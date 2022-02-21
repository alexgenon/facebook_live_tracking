package be.aufildemescoutures.domain

import java.time.LocalDateTime

data class Comment(
    val commentId: String,
    val user: FacebookUser,
    val item: Int,
    val timestamp: LocalDateTime,
    val fullComment: String,
    val action: ActionType
){
    val id =commentId+"#"+item
}

data class FacebookUser(val name: String, val id: String) {
    companion object {
        @JvmStatic
        val NoRecordedUser = FacebookUser("<No user>", "<No user>")
    }
}

enum class ActionType { BUY, REVIEW, QUESTION, NOTHING }
typealias CommentList = List<Comment>
