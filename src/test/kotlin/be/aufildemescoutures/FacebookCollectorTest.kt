package be.aufildemescoutures

import be.aufildemescoutures.domain.ActionType
import be.aufildemescoutures.infrastructure.facebook.FacebookCollector
import be.aufildemescoutures.infrastructure.facebook.VideoStream
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import org.hamcrest.CoreMatchers
import org.jboss.logging.Logger
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalDateTime
import javax.ws.rs.core.MediaType

@QuarkusTest
@QuarkusTestResource(FacebookMockExtension::class)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class FacebookCollectorTest {
    val comment_stream = """
    data:{"from":{"name":"Ariane Collard","id":"1088099037893657"},"created_time":"2021-12-19T05:38:29+0000","message":"je prends le 5","id":"461572928685550_461573375352172"}
    data:{"from":{"name":"Nathalie Macquoi","id":"1088099037893657"},"created_time":"2021-12-19T05:38:40+0000","message":"revoir le 4, 2, 39, 11","id":"461572928685550_461573495352160"}
""".trimIndent().split("\n")

    val LOG = Logger.getLogger(VideoStream::class.java)
    val video = "1234"

    @Test
    @Order(1)
    fun testParsingBuy() {
        val parsedComment = FacebookCollector.fromFacebook(comment_stream[0])
        LOG.debug(parsedComment)
        assertAll("Correctly parse a comment",
            { assertEquals(5, parsedComment[0].item) },
            { assertEquals(LocalDateTime.parse("2021-12-19T05:38:29+0000", FacebookCollector.facebookDatePattern)
                , parsedComment[0].timestamp) },
            { assertEquals("Ariane Collard", parsedComment[0].user.name) },
            { assertEquals(ActionType.BUY, parsedComment[0].action) }
        )
    }

    @Test
    @Order(2)
    fun testTracking() {
        RestAssured.given()
            .`when`()
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("video", video)
            .param("access_token","5678")
            .post("/live")
            .then()
            .statusCode(200)
            .body(CoreMatchers.`is`(video))
    }

    @Test
    @Order(3)
    fun testNonExistentCommentStream() {
        RestAssured.given()
            .`when`()
            .get("/live/coucou_gamin")
            .then()
            .statusCode(404)
    }

    @Test
    @Order(4)
    fun testCommentStream() {
        RestAssured.given()
            .`when`()
            .get("/live/$video")
            .then()
            .statusCode(200)
            .body(CoreMatchers.`is`("Hello RESTEasy Reactive"))
    }

    @Test
    @Order(5)
    fun testHardcodedStream(){
        RestAssured.given()
            .`when`()
            .get("/live/test")
            .then()
            .statusCode(200)
            .body(CoreMatchers.`is`("Hello RESTEasy Reactive"))
    }
}