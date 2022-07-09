package be.aufildemescoutures

import be.aufildemescoutures.infrastructure.facebook.VideoStream
import be.aufildemescoutures.mock.MockConfiguration
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.logging.Logger
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import java.net.URLEncoder
import javax.enterprise.inject.Default
import javax.inject.Inject

@QuarkusTest
class VideoStreamTest {
    private val LOG=Logger.getLogger(javaClass)

    private val comment_rate = "ten_per_second"
    private val fields = URLEncoder.encode("from{name,id},created_time,message", "UTF-8")

    @Inject
    @RestClient
    lateinit var videoStream: VideoStream

    @Inject
    lateinit var mockConfiguration: MockConfiguration

    @Test
    fun testVideoStream(){
        val comments = videoStream.getComments("1234", "5678",fields,comment_rate)
            .collect().asList().await().indefinitely()
        LOG.debug(comments)
        assertAll("Comments retrieval",
            { assertEquals(mockConfiguration.totalNumber,comments.size)}
        )
    }

}
