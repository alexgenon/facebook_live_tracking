package be.aufildemescoutures.api

import be.aufildemescoutures.domain.Comment
import be.aufildemescoutures.domain.CommentList
import be.aufildemescoutures.infrastructure.facebook.FacebookCollector
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.groups.MultiCollect
import kotlinx.coroutines.flow.cancellable
import org.jboss.logging.Logger
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("live")
class LiveTracker {
    private var LOG = Logger.getLogger(javaClass)

    @Inject
    @field:Default
    lateinit var facebookCollector: FacebookCollector

    val liveMap = mutableMapOf<String,Multi<CommentList>>()

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    fun startLiveTracking(
        @FormParam("video") video: String,
        @FormParam("access_token") token: String
    ) : String {
        LOG.debug("starting live tracking for video ${video}")
        val collectComments = facebookCollector.collectComments(video, token)
        this.liveMap[video] = collectComments
        return video
    }

    @GET
    @Path("{video}")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    fun getComments(@PathParam("video") video:String): Multi<CommentList> {
        val collectedComments = this.liveMap[video]
        /*if(collectedComments != null){
            return Response.ok(collectedComments).build()
        } else {
            return Response.status(404,"$video not being recorded").build()
        }*/
        return collectedComments ?: Multi.createFrom().empty()
    }
}