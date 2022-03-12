package be.aufildemescoutures.domain.live_tracking.validation

import be.aufildemescoutures.domain.Comment
import be.aufildemescoutures.domain.CommentId
import be.aufildemescoutures.domain.live_tracking.LiveEvent
import io.quarkus.vertx.ConsumeEvent
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.operators.multi.processors.UnicastProcessor
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@ApplicationScoped
class ValidationService {
    private val LOG = Logger.getLogger(javaClass)

    private var processor = UnicastProcessor.create<Comment>()

    @Inject
    @field:Default
    lateinit var validationRepository:ValidationRepository

    @ConsumeEvent(LiveEvent.nonCuratedComments)
    fun newEventToReview(comment: Comment) {
        validationRepository.newCommentToValidate(comment)
        this.processor.onNext(comment)
    }

    @ConsumeEvent(LiveEvent.controlBus)
    fun liveStopped(event:String) {
        if(LiveEvent.stopEvent.equals(event)){
            LOG.info("Live is stopped, sending onComplete event using processor")
            this.processor.onComplete()
        }
    }

    fun commentValidated(commentId:CommentId)=validationRepository.commentValidated(commentId )
    fun allPendingComments() = validationRepository.allCommentsPendingValidation()

    fun streamOfCommentsToValidate():Multi<Comment> {
        if(processor.hasSubscriber()){
            /* reset the unicast processor, that will lead to the previous consumer to be shutdown
            But I could not find a way to multicast those. An alternative is to create multiple unicast processors
            and call onNext for each of them.
             */
            processor= UnicastProcessor.create<Comment>()
        }
        return processor
    }
}