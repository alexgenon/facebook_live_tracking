package be.aufildemescoutures

import be.aufildemescoutures.infrastructure.facebook.VideoStream
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
    @Inject
    @RestClient
    lateinit var videoStream: VideoStream

    private val comment_rate = "ten_per_second"
    private val fields = URLEncoder.encode("from{name,id},created_time,message", "UTF-8")

    private val LOG=Logger.getLogger(javaClass)

    @Test
    fun testVideoStream(){
        val comments = videoStream.getComments("1234", "5678",fields,comment_rate)
            .collect().asList().await().indefinitely()
        LOG.debug(comments)
        assertAll("Comments retrieval",
            { assertEquals(351,comments.size)}
        )
    }

}
