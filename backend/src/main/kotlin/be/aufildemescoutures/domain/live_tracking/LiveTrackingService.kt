package be.aufildemescoutures.domain.live_tracking

import be.aufildemescoutures.domain.sales.InventoryService
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
    private lateinit var commentStream: Cancellable

    @Inject
    @field:Default
    lateinit var facebookCollector: FacebookCollector

    @Inject
    @field:Default
    lateinit var inventoryService: InventoryService

    @Inject
    lateinit var eventBus: EventBus;

    fun startTracking(video: String, liveId: String, itemsCount:Int) {
        LOG.debug("starting live tracking for video ${video}, related to live $liveId")
        this.videoId = video
        this.liveId = liveId
        inventoryService.newLive(liveId,itemsCount)

        this.commentStream = facebookCollector
            .collectComments(video)
            // We get a stream of List<Comment>, flattening it
            .flatMap { commentList -> Multi.createFrom().iterable(commentList) }
            .onFailure().transform { error ->
                    LOG.error("Failure during get comment",error)
                    RuntimeException(error)
            }
            .subscribe().with { comment -> eventBus.publish(LiveEvent.newComment, comment) }

    }

    fun getLive() = this.liveId

    fun stopTracking(){
        if(this::commentStream.isInitialized) {
            this.commentStream.cancel()
            eventBus.publish(LiveEvent.controlBus,LiveEvent.stopEvent)
            this.videoId=null
            this.liveId=null
        }
    }
}
