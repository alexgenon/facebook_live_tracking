package be.aufildemescoutures.domain.live_tracking.validation

import be.aufildemescoutures.domain.core.Comment
import be.aufildemescoutures.domain.core.CommentId
import be.aufildemescoutures.domain.core.customer.Customer
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ValidationRepository {
    private val commentsPendingValidation = mutableMapOf<CommentId,Comment>()
    private val archivedComments =  mutableMapOf<Customer,MutableList<Comment>>()
    fun newCommentToValidate(comment: Comment):Unit  { commentsPendingValidation[comment.id] = comment }
    fun removeComment(commentId: CommentId) {commentsPendingValidation.remove(commentId)}
    fun allCommentsPendingValidation()=commentsPendingValidation.values.toList()
    fun getComment(commentId: CommentId): Comment? = commentsPendingValidation.get(commentId)
    fun archiveComment(comment: Comment) {
        archivedComments.getOrPut(comment.user) { mutableListOf() }.add(comment)
        removeComment(comment.id)
    }
    fun allArchivedComments() = archivedComments
    fun archivedCommentsForCustomer(name: Customer):List<Comment>? = archivedComments[name]

}
