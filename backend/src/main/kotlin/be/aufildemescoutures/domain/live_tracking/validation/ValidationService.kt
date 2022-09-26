package be.aufildemescoutures.domain.live_tracking.validation

import be.aufildemescoutures.domain.customer.CustomerService
import be.aufildemescoutures.domain.live_tracking.core.comment.ActionType
import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import be.aufildemescoutures.domain.live_tracking.core.comment.CommentId
import be.aufildemescoutures.domain.live_tracking.core.comment.Contest
import be.aufildemescoutures.domain.live_tracking.core.customer.Customer
import be.aufildemescoutures.domain.live_tracking.core.live_event.LiveEvent
import io.quarkus.vertx.ConsumeEvent
import io.vertx.mutiny.core.eventbus.EventBus
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.NotFoundException

@ApplicationScoped
class ValidationService {
    private val LOG = Logger.getLogger(javaClass)

    private var contest: Contest = Contest.NONE

    @Inject
    lateinit var commmentBus: EventBus;

    @Inject
    @field:Default
    lateinit var validationRepository: ValidationRepository

    @Inject
    @field:Default
    lateinit var customerService: CustomerService

    @ConsumeEvent(LiveEvent.newComment)
    fun newEventToReview(comment: Comment) {
        LOG.debug("Comment ${comment.id} will be published as new comment")
        if(this.contest != Contest.NONE) {
            val commentForContest = this.contest.newCommentForContest(comment.copy(action = ActionType.CONTEST))
            this.commmentBus.publish(LiveEvent.contestComment,
                LiveEvent.build(commentForContest,LiveEvent.contestComment))
        } else {
            validationRepository.newCommentToValidate(comment)
            // TODO: refactor : Publish LiveEvent instead of Comment
            this.commmentBus.publish(LiveEvent.commentToValidate, comment)
        }
    }

    @ConsumeEvent(LiveEvent.controlBus)
    fun liveStopped(event: String) {
        if (LiveEvent.stopEvent.equals(event)) {
            LOG.info("Live is stopped")
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

    fun startContestMode(keyword: String):Contest {
        return contestSwitch(Contest(keyword))
    }

    fun stopContestMode():Contest{
        return contestSwitch(Contest.NONE)
    }

    private fun contestSwitch(contest: Contest):Contest{
        this.contest = contest
        this.commmentBus.publish(LiveEvent.contestSwitch,LiveEvent.build(this.contest!!,LiveEvent.contestSwitch))
        return this.contest
    }
}
