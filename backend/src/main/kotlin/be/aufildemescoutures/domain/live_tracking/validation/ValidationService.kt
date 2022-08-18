package be.aufildemescoutures.domain.live_tracking.validation

import be.aufildemescoutures.domain.ActionType
import be.aufildemescoutures.domain.Comment
import be.aufildemescoutures.domain.CommentId
import be.aufildemescoutures.domain.FacebookUser
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

    @Inject
    lateinit var commmentBus: EventBus;

    @Inject
    @field:Default
    lateinit var validationRepository:ValidationRepository

    @ConsumeEvent(LiveEvent.newComment)
    fun newEventToReview(comment: Comment) {
        validationRepository.newCommentToValidate(comment)
        LOG.debug("Comment ${comment.id} will be published as new comment")
        this.processor.onNext(comment) // will end up as a server sent event via LiveTrackerApi
        this.commmentBus.publish(LiveEvent.commentToValidate,comment) // will be sent to all WS connections
    }

    @ConsumeEvent(LiveEvent.controlBus)
    fun liveStopped(event:String) {
        if(LiveEvent.stopEvent.equals(event)){
            LOG.info("Live is stopped, sending onComplete event to processor")
            this.processor.onComplete()
        }
    }

    @ConsumeEvent(LiveEvent.commentValidated)
    fun commentValidated(comment:Comment){
        LOG.debug("Comment $comment validated")
        LiveEvent.busesInterestedIn(comment.action)
            .forEach {
                LOG.debug("Sending to $it $comment")
                this.commmentBus.publish(it,comment)
            }
        validationRepository.archiveComment(comment)
    }

    fun commentValidated(commentId:CommentId, action:String) {
        LOG.debug("Comment $commentId validated with $action")
        val updatedComment = validationRepository.getComment(commentId)?.copy(action= ActionType.valueOf(action))

        if(updatedComment!=null){
            commentValidated(updatedComment)
        } else {
            val errorMessage = "$commentId is not a recognized comment"
            LOG.error(errorMessage)
            throw NotFoundException(errorMessage) // Should not send a jax-rs exception from a domain service 🤷‍
        }

    }

    fun allPendingComments() = validationRepository.allCommentsPendingValidation()

    fun streamOfCommentsPendingValidation():Multi<Comment> {
        LOG.info("Call to get the stream of comments pending validation")
        if(processor.hasSubscriber()){
            /* reset the unicast processor, that will lead to the previous consumer to be shutdown
            TODO: Maybe check what could be done with processor.broadcast()
             */
            LOG.debug("Reset of processor, existing on is $processor")
            processor.onComplete()
            processor= UnicastProcessor.create<Comment>()
        }
        validationRepository.allCommentsPendingValidation()
            .forEach {
                LOG.debug("Comment ${it.id} will be published as a pending validation")
                processor.onNext(it)
            }
        return processor
    }

    fun archivedComments(): Map<FacebookUser,List<Comment>> = validationRepository.allArchivedComments()
}
