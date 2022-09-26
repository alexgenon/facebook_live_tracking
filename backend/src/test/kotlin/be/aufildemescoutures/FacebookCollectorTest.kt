package be.aufildemescoutures

import be.aufildemescoutures.domain.live_tracking.core.comment.ActionType
import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import be.aufildemescoutures.infrastructure.facebook.FacebookCollector
import be.aufildemescoutures.infrastructure.facebook.VideoStream
import be.aufildemescoutures.mock.MockServer
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.hamcrest.CoreMatchers
import org.jboss.logging.Logger
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import javax.ws.rs.core.MediaType

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class FacebookCollectorTest {
    val comment_stream =MockServer.comment_stream.split("\n")

    val LOG = Logger.getLogger(VideoStream::class.java)
    val video = "1234"


    @Test
    @Order(1)
    fun testParsingBuy() {
        var parsedComment = FacebookCollector.fromFacebook(comment_stream[0])
        LOG.debug(parsedComment)
        assertAll("Correctly parse a buy comment - prend",
            { assertEquals(5, parsedComment[0].item) },
            { assertEquals(LocalDateTime.parse("2021-12-19T05:38:29").toInstant(TimeZone.UTC)
                , parsedComment[0].timestamp) },
            { assertEquals("%NAME", parsedComment[0].user.fullName()) },
            { assertEquals(ActionType.BUY, parsedComment[0].action) }
        )
        parsedComment = FacebookCollector.fromFacebook(comment_stream[6])
        LOG.debug(parsedComment)
        assertAll("Correctly parse a buy comment - achète",
            { assert(parsedComment.map(Comment::item).toSet().equals(setOf(14,22))) },
            { assertEquals(ActionType.BUY, parsedComment[0].action) },
            { assertEquals(ActionType.BUY, parsedComment[1].action) }
        )
    }

    @Test
    @Order(2)
    fun testParsingReview(){
        var parsedComment = FacebookCollector.fromFacebook(comment_stream[1])
        LOG.debug(parsedComment)
        assertAll("Correctly parse a review comment",
            { assertEquals(4,parsedComment.size)},
            { assert(parsedComment.map(Comment::item).toSet().equals(setOf(4, 2, 39, 11))) },
            { assertEquals(LocalDateTime.parse("2021-12-19T05:38:40").toInstant(TimeZone.UTC)
                , parsedComment[0].timestamp) },
            { assertEquals("%NAME", parsedComment[0].user.fullName()) },
            { assertEquals(ActionType.REVIEW, parsedComment[0].action) }
        )
    }


    @Test
    @Order(2)
    fun testStartTracking() {
        RestAssured.given()
            .`when`()
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("video", video)
            .param("liveId", video)
            .post("/live")
            .then()
            .statusCode(200)
            .body(CoreMatchers.`is`("Started collection of $video"))
    }
}
