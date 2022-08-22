package be.aufildemescoutures.domain.review

import be.aufildemescoutures.domain.core.Comment

data class Question(val comment:Comment, val answer: String,val status: QuestionStatus){
    fun getQuestionText(): String = comment.fullComment
    fun getId()=comment.id.id
    fun getAuthor()=comment.user.fullName()
    constructor(comment: Comment) : this(comment,"",QuestionStatus.UNANSWERED)
}

enum class QuestionStatus{ANSWERED,UNANSWERED}
