package be.aufildemescoutures.api

import io.quarkus.arc.profile.IfBuildProfile
import io.smallrye.mutiny.Multi
import org.jboss.logging.Logger
import java.time.Duration
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@ApplicationScoped
@IfBuildProfile("dev")
@Path("mock")
class MockServer {
    private var LOG = Logger.getLogger(javaClass)
    lateinit var multi: Multi<String>

    @Path("{video}/live_comments")
    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    fun getComments(
        @PathParam("video") video: String,
        @QueryParam("access_token") accessToken: String?,
        @QueryParam("fields") fields: String?,
        @QueryParam("comment_rate") commentRate: String?
    ): Multi<String> {
        LOG.debug("New call to get comments")
        val split = comment_stream.split("\n")
        multi = Multi.createFrom().ticks()
            .every(Duration.ofSeconds(3))
            .map { split[(it % (split.size)).toInt()].replace("%ID", it.toString()) }
            .map {
                LOG.debug("About to send $it")
                it
            }
        return multi
    }

    companion object {
        val comment_stream = """
    {"from":{"name":"Jeff Bezos","id":"1088099037893657"},"created_time":"2021-12-19T05:38:29+0000","message":"je prends le 5","id":"%ID"}
    {"from":{"name":"Bill Gates","id":"1088099037893657"},"created_time":"2021-12-19T05:38:40+0000","message":"revoir le 4, 2, 39 et le 11","id":"%ID"}
    {"from":{"name":"Joe Biden","id":"1231219037893657"},"created_time":"2021-12-19T05:43:40+0000","message":"Je voudrais revoir le 9 et le 1","id":"%ID"}
    {"from":{"name":"Donald Trump","id":"1231219037893657"},"created_time":"2021-12-19T05:44:40+0000","message":"Fake news, sleepy Joe !","id":"%ID"}
    {"created_time":"2021-12-19T05:44:40+0000","message":"Tr\u00e8s beau celui la aussi \ud83e\udd70","id":"%ID"}
    {"created_time":"2021-12-19T05:44:40+0000","message":"Je prend le n 10 svp","id":"%ID"}
    {"from":{"name":"Stéphanie de Monaco","id":"1231219037893657"},"created_time":"2022-02-20T15:43:40+0000","message":"J'achète le 14 et le 22","id":"%ID"}
""".trimIndent()
    }
}

