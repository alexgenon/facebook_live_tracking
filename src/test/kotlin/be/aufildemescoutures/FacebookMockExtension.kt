package be.aufildemescoutures

import be.aufildemescoutures.mock.MockServer
import io.muserver.*
import io.muserver.MuServerBuilder.httpServer
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.jboss.logging.Logger

/*
First tried to use MuServer to have mock Facebook API during integration testing.
MuServer supports Server Sent Events compared to WireMockServer.
But then I also wanted to use the mock in dev mode and it was not possible to instantiate it in dev move
Thus, I went with a home grown api endpoint to mock FB API (package be.aufildemescoutures.mock.MockServer)
 */
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
