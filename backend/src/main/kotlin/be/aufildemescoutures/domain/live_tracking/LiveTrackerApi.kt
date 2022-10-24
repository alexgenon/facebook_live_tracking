package be.aufildemescoutures.domain.live_tracking

import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import be.aufildemescoutures.domain.live_tracking.core.comment.CommentId
import be.aufildemescoutures.domain.live_tracking.core.comment.ContestManagement
import be.aufildemescoutures.domain.live_tracking.validation.ValidationService
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jboss.logging.Logger
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("live")
class LiveTrackerApi {
    private var LOG = Logger.getLogger(javaClass)
    @Inject
    @field:Default
    lateinit var liveTrackingService: LiveTrackingService
    @Inject
    @field:Default
    lateinit var validationService: ValidationService

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    fun startLiveTracking(
        @FormParam("video") video: String,
        @FormParam("liveId") liveId: String,
        @FormParam("items") itemsCount:Int
    ) : String {
        liveTrackingService.startTracking(video,liveId,itemsCount)
        return "Started collection of $video"
    }

    @DELETE
    fun stopLiveTracking():String {
        liveTrackingService.stopTracking()
        return "Collection of comments stopped"
    }

    @GET
    fun getLiveStatus(): String {
        return liveTrackingService.getLive() ?: throw NotFoundException("No Live running")
    }

    @GET
    @Path("/comments/validation/list")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCommentsPendingValidation():Collection<Comment> = validationService.allPendingComments()

    @POST
    @Path("/comments/validation/contest")
    fun startContest(@QueryParam("keyword") keyword:String): ContestManagement = validationService.startContestMode(keyword)

    @DELETE
    @Path("/comments/validation/contest")
    fun stopContest():ContestManagement = validationService.stopContestMode()

    @POST
    @Path("/comments/validation/{id}")
    fun commentValidated(@PathParam("id") id: String,@FormParam("action") action:String) =
        validationService.commentValidated(CommentId(id), action)

    @GET
    @Path("/comments/archives/")
    fun archivedComments() = validationService.archivedComments().map { Pair(Json.encodeToString(it.key), it.value) }

    @GET
    @Path("/comments/archives/{name}")
    fun allCommentsForCustomer(@PathParam("name") name:String) =
        validationService.allCommentsForCustomer(name)
}
