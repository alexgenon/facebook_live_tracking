package be.aufildemescoutures

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import javax.ws.rs.core.MediaType


@QuarkusTest
class LiveTrackerAPITest {
    @Test
    fun testStartLive(){
        given()
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .formParam("video","abc")
            .formParam("liveId","123")
            .formParam("items","100")
            .`when`()
            .post("/live")
            .then()
            .statusCode(200)
    }
}
