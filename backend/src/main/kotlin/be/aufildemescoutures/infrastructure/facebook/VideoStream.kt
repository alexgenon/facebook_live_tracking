package be.aufildemescoutures.infrastructure.facebook

import io.smallrye.mutiny.Multi
import kotlinx.coroutines.flow.Flow
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("{video}")
@RegisterRestClient
interface VideoStream {
    @Path("live_comments")
    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    fun getComments(
        @PathParam("video") video: String,
        @QueryParam("access_token") accessToken: String,
        @QueryParam("fields") fields: String,
        @QueryParam("comment_rate") commentRate: String
    ): Multi<String>
}