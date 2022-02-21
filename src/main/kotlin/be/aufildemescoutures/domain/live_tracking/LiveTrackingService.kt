package be.aufildemescoutures.domain.live_tracking

import be.aufildemescoutures.domain.ActionType
import be.aufildemescoutures.domain.Comment
import be.aufildemescoutures.domain.inventory.InventoryService
import be.aufildemescoutures.infrastructure.facebook.FacebookCollector
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.subscription.Cancellable
import io.vertx.mutiny.core.eventbus.EventBus
import org.jboss.logging.Logger
import java.lang.RuntimeException
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@ApplicationScoped
class LiveTrackingService {
    val LOG = Logger.getLogger(javaClass)
    var liveId: String? = null
    var videoId: String? = null
    lateinit var commentStream: Cancellable

    @Inject
    @field:Default
    lateinit var facebookCollector: FacebookCollector

    @Inject
    lateinit var commmentBus: EventBus;

    fun startTracking(video: String, liveId: String) {
        LOG.debug("starting live tracking for video ${video}, related to live $liveId")
        this.videoId = video
        this.liveId = liveId

        this.commentStream = facebookCollector
            .collectComments(video)
            // We get a stream of list of comments, flattening all this
            .flatMap { commentList -> Multi.createFrom().iterable(commentList) }
            .onFailure().transform { error ->
                    LOG.error("Failure during get comment",error)
                    RuntimeException(error)
            }
            .subscribe().with { comment -> commmentBus.publish(LiveEvent.nonCuratedComments, comment) }

    }

    fun stopTracking(){
        if(this::commentStream.isInitialized) {
            this.commentStream.cancel()
            commmentBus.publish(LiveEvent.controlBus,LiveEvent.stopEvent)
        }
    }
}