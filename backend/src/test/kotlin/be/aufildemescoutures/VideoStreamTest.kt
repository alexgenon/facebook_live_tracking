package be.aufildemescoutures

import be.aufildemescoutures.infrastructure.facebook.VideoStream
import be.aufildemescoutures.mock.MockConfiguration
import io.quarkus.test.junit.QuarkusTest
import io.smallrye.mutiny.helpers.test.AssertSubscriber
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.logging.Logger
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.net.URLEncoder
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
        val subscriber = comments
            .subscribe()
            .withSubscriber(AssertSubscriber.create((mockConfiguration.totalNumber?:0).toLong()))

        val result = subscriber
            .awaitCompletion()
            .assertCompleted()
            .items

        assertAll("Comments retrieval",
            { assertEquals(mockConfiguration.totalNumber,result.size)}
        )
    }
}
