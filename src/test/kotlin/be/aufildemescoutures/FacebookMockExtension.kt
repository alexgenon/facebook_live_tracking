package be.aufildemescoutures

import com.github.tomakehurst.wiremock.WireMockServer
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import com.github.tomakehurst.wiremock.client.WireMock.*
import javax.ws.rs.core.MediaType


class FacebookMockExtension : QuarkusTestResourceLifecycleManager {
    private lateinit var wireMockServer: WireMockServer

    private val comment_stream = """
    data:{"from":{"name":"Ariane Collard","id":"1088099037893657"},"created_time":"2021-12-19T05:38:29+0000","message":"je prends le 5","id":"461572928685550_461573375352172"}
    
    data:{"from":{"name":"Nathalie Macquoi","id":"1088099037893657"},"created_time":"2021-12-19T05:38:40+0000","message":"revoir le 4, 2, 39, 11","id":"461572928685550_461573495352160"}
    
""".trimIndent()

    override fun start(): MutableMap<String, String> {
        wireMockServer = WireMockServer()
        wireMockServer.start()
        val url =
            "/1234/live_comments"
        stubFor(
            get(urlPathEqualTo(url))
                .withQueryParam("access_token", equalTo("5678"))
                .withQueryParam("fields", equalTo("from{name,id},created_time,message"))
                .withQueryParam("comment_rate", equalTo("ten_per_second"))
                .willReturn(okForContentType(MediaType.SERVER_SENT_EVENTS, this.comment_stream))
        )

        return mutableMapOf(Pair
            ("quarkus.rest-client.\"be.aufildemescoutures.infrastructure.facebook.VideoStream\".url"
            ,wireMockServer.baseUrl())
        )
    }

    override fun stop() {
        wireMockServer.stop()
    }
}