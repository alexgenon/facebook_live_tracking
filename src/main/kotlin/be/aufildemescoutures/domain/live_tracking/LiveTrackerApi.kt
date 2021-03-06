package be.aufildemescoutures.domain.live_tracking

import be.aufildemescoutures.domain.ActionType
import be.aufildemescoutures.domain.Comment
import be.aufildemescoutures.domain.CommentId
import be.aufildemescoutures.domain.FacebookUser
import be.aufildemescoutures.domain.live_tracking.validation.ValidationService
import io.smallrye.mutiny.Multi
import org.jboss.logging.Logger
import org.jboss.resteasy.reactive.RestSseElementType
import java.time.LocalDateTime
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
    @Path("test_json")
    @Produces(MediaType.APPLICATION_JSON)
    fun getTest() = Comment("test", FacebookUser.NoRecordedUser,100, LocalDateTime.now(),"test commentaire",ActionType.REVIEW)

    @GET
    @Path("/comments/validation/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestSseElementType(MediaType.APPLICATION_JSON)
    fun getStreamToValidate():Multi<Comment> = validationService.streamOfCommentsPendingValidation()

    @GET
    @Path("/comments/validation/list")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCommentsPendingValidation():Collection<Comment> = validationService.allPendingComments()

    @POST
    @Path("/comments/validation/{id}")
    fun commentValidated(@PathParam("id") id: String,@FormParam("action") action:String) = validationService.commentValidated(
        CommentId(id), action)
}