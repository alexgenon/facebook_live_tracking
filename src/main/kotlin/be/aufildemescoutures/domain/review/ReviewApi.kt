package be.aufildemescoutures.domain.review

import be.aufildemescoutures.domain.review.Question
import be.aufildemescoutures.domain.review.ReviewService
import io.smallrye.mutiny.Multi
import org.jboss.resteasy.reactive.RestSseElementType
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

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