package be.aufildemescoutures.domain.review

import io.smallrye.mutiny.Multi
import org.jboss.resteasy.reactive.RestSseElementType
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("reviews")
class ReviewApi {
    @Inject
    @field:Default
    lateinit var reviewService: ReviewService

    @GET
    @Path("items/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestSseElementType(MediaType.APPLICATION_JSON)
    fun getItemsStream(): Multi<Int> = reviewService.reviewStream()

    @POST
    @Path("items/{id}/reviewed")
    fun itemReviewed(@PathParam("id") itemId:Int) = reviewService.itemReviewed(itemId)

    @GET
    @Path("questions/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestSseElementType(MediaType.APPLICATION_JSON)
    fun getQuestionsStream(): Multi<Question> = reviewService.questionsStream()

    @POST
    @Path("questions/{id}/answered")
    fun questionAnswered(@PathParam("id")questionId:String) = reviewService.questionAnswered(questionId)

}
