package be.aufildemescoutures.domain.live_tracking.validation

import be.aufildemescoutures.domain.Comment
import be.aufildemescoutures.domain.live_tracking.LiveEvent
import io.quarkus.vertx.ConsumeEvent
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.operators.multi.processors.UnicastProcessor
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ValidationService {
    private val LOG = Logger.getLogger(javaClass)

    private var processor = UnicastProcessor.create<Comment>()

    @ConsumeEvent(LiveEvent.nonCuratedComments)
    fun newEventToReview(comment: Comment) = this.processor.onNext(comment)

    @ConsumeEvent(LiveEvent.controlBus)
    fun liveStopped(event:String) {
        if(LiveEvent.stopEvent.equals(event)){
            LOG.info("Live is stopped, sending onComplete event using processor")
            this.processor.onComplete()
        }
    }

    fun commentsToValidate():Multi<Comment> {
        if(processor.hasSubscriber()){
            processor= UnicastProcessor.create<Comment>()
        }
        return processor
    }
}