package be.aufildemescoutures

import io.muserver.*
import io.muserver.MuServerBuilder.httpServer
import io.muserver.handlers.ResourceHandlerBuilder
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.jboss.logging.Logger


class FacebookMockExtension : QuarkusTestResourceLifecycleManager {
    private lateinit var mockServer:MuServer
    private val LOG= Logger.getLogger(FacebookMockExtension::class.java)

    private val comment_stream = """
    data:{"from":{"name":"Ariane Collard","id":"1088099037893657"},"created_time":"2021-12-19T05:38:29+0000","message":"je prends le 5","id":"461572928685550_461573375352172"}
    
    data:{"from":{"name":"Nathalie Macquoi","id":"1088099037893657"},"created_time":"2021-12-19T05:38:40+0000","message":"revoir le 4, 2, 39, 11","id":"461572928685550_461573495352160"}
    
""".trimIndent()

    override fun start(): MutableMap<String, String> {
        val url = "/1234/live_comments"
        mockServer = httpServer()
            .addHandler(
                Method.GET,
                url
            ) { request: MuRequest?, response: MuResponse?, pathParams: Map<String?, String?>? ->
                val publisher = SsePublisher.start(request, response)
                Thread { count(publisher) }.start()
            }
            .start()

        return mutableMapOf(Pair
            ("quarkus.rest-client.\"be.aufildemescoutures.infrastructure.facebook.VideoStream\".url"
            ,mockServer.httpUri().toString())
        )
    }

    fun count(publisher: SsePublisher){
        comment_stream.split("\n").forEach {
            publisher.send(it)
            Thread.sleep(500)
        }
        publisher.close()
    }
    override fun stop() {
        mockServer.stop()
    }
}