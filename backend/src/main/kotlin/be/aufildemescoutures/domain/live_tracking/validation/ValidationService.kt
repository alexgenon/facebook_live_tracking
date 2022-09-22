package be.aufildemescoutures.domain.live_tracking.validation

import be.aufildemescoutures.domain.core.ActionType
import be.aufildemescoutures.domain.core.Comment
import be.aufildemescoutures.domain.core.CommentId
import be.aufildemescoutures.domain.core.customer.Customer
import be.aufildemescoutures.domain.customer.CustomerService
import be.aufildemescoutures.domain.live_tracking.LiveEvent
import io.quarkus.vertx.ConsumeEvent
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.operators.multi.processors.UnicastProcessor
import io.vertx.mutiny.core.eventbus.EventBus
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.NotFoundException

@ApplicationScoped
class ValidationService {
    private val LOG = Logger.getLogger(javaClass)

    private var processor = UnicastProcessor.create<Comment>()
    private var contestMode = false
    private var contestKeyword = ""

    @Inject
    lateinit var commmentBus: EventBus;

    @Inject
    @field:Default
    lateinit var validationRepository: ValidationRepository

    @Inject
    @field:Default
    lateinit var customerService: CustomerService

    @ConsumeEvent(LiveEvent.newComment)
    fun newEventToReview(originalComment: Comment) {
        val comment = if(contestMode) {
            originalComment.copy(action = ActionType.CONTEST)
        } else {
            originalComment
        }
        LOG.debug("Comment ${comment.id} will be published as new comment")
        validationRepository.newCommentToValidate(comment)
        this.processor.onNext(comment) // will end up as a server sent event via LiveTrackerApi
        this.commmentBus.publish(LiveEvent.commentToValidate, comment) // will be sent to all WS connections
    }

    @ConsumeEvent(LiveEvent.controlBus)
    fun liveStopped(event: String) {
        if (LiveEvent.stopEvent.equals(event)) {
            LOG.info("Live is stopped, sending onComplete event to processor")
            this.processor.onComplete()
        }
    }

    @ConsumeEvent(LiveEvent.commentValidated)
    fun commentValidated(comment: Comment) {
        LOG.debug("Comment $comment validated")
        LiveEvent.busesInterestedIn(comment.action)
            .forEach {
                LOG.debug("Sending to $it $comment")
                this.commmentBus.publish(it, comment)
            }
        validationRepository.archiveComment(comment)
    }

    fun commentValidated(commentId: CommentId, action: String) {
        LOG.debug("Comment $commentId validated with $action")
        val updatedComment = validationRepository.getComment(commentId)?.copy(action = ActionType.valueOf(action))

        if (updatedComment != null) {
            commentValidated(updatedComment)
        } else {
            val errorMessage = "$commentId is not a recognized comment"
            LOG.error(errorMessage)
            throw NotFoundException(errorMessage) // Should not send a jax-rs exception from a domain service 🤷‍
        }

    }

    fun allPendingComments() = validationRepository.allCommentsPendingValidation()

    fun archivedComments(): Map<Customer, List<Comment>> = validationRepository.allArchivedComments()
    fun allCommentsForCustomer(name: String): List<Comment>? {
        val customer = customerService.findCustomer(name)
        val comments = customer?.let {
            (validationRepository.archivedCommentsForCustomer(customer)?: emptyList<Comment>())+
                    (validationRepository.allCommentsPendingValidation().filter { comment -> comment.user == customer })
        }
        return comments
    }

    fun startContestMode(keyword: String) {
        contestMode = true
        contestKeyword = keyword
    }

    fun stopContestMode(){
        contestMode = false
    }
}
