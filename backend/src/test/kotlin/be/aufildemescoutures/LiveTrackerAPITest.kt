package be.aufildemescoutures

import io.quarkus.test.junit.QuarkusTest
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.*
import org.junit.jupiter.api.Test
import javax.ws.rs.core.MediaType


@QuarkusTest
class LiveTrackerAPITest {
    @Test
    fun testStartLive(){
        Given {
            contentType(MediaType.APPLICATION_FORM_URLENCODED)
                formParam("video","abc")
                formParam("liveId","123")
                formParam("items","100")
        } When {
            post("/live")
        } Then {
            statusCode(200)
        }
    }

    @Test
    fun testCommentValidation(){
        Given{
            contentType(MediaType.APPLICATION_FORM_URLENCODED)
            formParam("video","abc")
            formParam("liveId","123")
            formParam("items","100")
        } When {
            post("/live")
        } Then {
            statusCode(200)
        }
        Thread.sleep(1000)

        When {
            get("/live/comments/validation/list")
        } Then {
            statusCode(200)
            log().all()
            body("fullComment",hasItems( "je prends le 5"))
            body("item",hasItems(5))
        }
    }
    @Test
    /* TODO: Needs to be reworked => add check that, in contest mode, only new contest proposal are issues
    *  */
    fun testContestMode(){
        Given{
            contentType(MediaType.APPLICATION_FORM_URLENCODED)
            formParam("video","abc")
            formParam("liveId","123")
            formParam("items","100")
        } When {
            post("/live")
        } Then {
            statusCode(200)
        }

        When {
            post("/live/comments/validation/contest?keyword=test")
        } Then {
            statusCode(200)
        }

        When {
            get("/live/comments/validation/list")
        } Then {
            statusCode(200)
        }
        When {
            delete("/live/comments/validation/contest")
        } Then{
            statusCode(200)
        }
        Thread.sleep(1000)
        When {
            get("/live/comments/validation/list")
        } Then {
            statusCode(200)
            log().all()
            body("action.last()", not(`is`("CONTEST")))
        }
    }
}
