package be.aufildemescoutures.domain

import java.time.LocalDateTime

data class Comment(val id:String, val user:FacebookUser, val item: Int, val timestamp: LocalDateTime, val fullComment: String,val action:ActionType)

data class FacebookUser (val name:String, val id:String)

enum class ActionType {BUY,REVIEW,NONE, QUESTION }
typealias CommentList=List<Comment>
