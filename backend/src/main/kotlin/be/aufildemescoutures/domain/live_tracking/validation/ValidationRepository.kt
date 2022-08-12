package be.aufildemescoutures.domain.live_tracking.validation

import be.aufildemescoutures.domain.Comment
import be.aufildemescoutures.domain.CommentId
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ValidationRepository {
    private val commentsPendingValidation = mutableMapOf<CommentId,Comment>()
    fun newCommentToValidate(comment: Comment):Unit  { commentsPendingValidation[comment.id] = comment }
    fun removeComment(commentId: CommentId) {commentsPendingValidation.remove(commentId)}
    fun allCommentsPendingValidation()=commentsPendingValidation.values.toList()
    fun getComment(commentId: CommentId): Comment? = commentsPendingValidation.get(commentId)
}