package be.aufildemescoutures

import be.aufildemescoutures.api.MockServer
import io.muserver.*
import io.muserver.MuServerBuilder.httpServer
import io.muserver.handlers.ResourceHandlerBuilder
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.jboss.logging.Logger

class FacebookMockExtension : QuarkusTestResourceLifecycleManager {
    private lateinit var mockServer:MuServer
    private val LOG=Logger.getLogger(javaClass)

    private val comment_stream = MockServer.comment_stream

    override fun start(): MutableMap<String, String> {
        val url = "/1234/live_comments"
        mockServer = httpServer()
            .addHandler(
                Method.GET,
                url
            ) { request: MuRequest?, response: MuResponse?, _: Map<String?, String?>? ->
                val publisher = SsePublisher.start(request, response)
                Thread { count(publisher) }.start()
            }
            .start()

        val serverUrl = mockServer.httpUri().toString()
        LOG.debug("${javaClass.canonicalName} will listen on $serverUrl")
        return mutableMapOf(Pair
            ("quarkus.rest-client.\"be.aufildemescoutures.infrastructure.facebook.VideoStream\".url"
            , serverUrl
        )
        )
    }

    fun count(publisher: SsePublisher){
        comment_stream.split("\n").forEach {
            LOG.debug("Will sent $it")
            publisher.send(it)
            Thread.sleep(50)
        }
        publisher.close()
    }
    override fun stop() {
        mockServer.stop()
    }
}