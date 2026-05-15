package be.aufildemescoutures

import be.aufildemescoutures.domain.live_tracking.core.comment.ActionType
import be.aufildemescoutures.domain.sales.InventoryService
import io.quarkus.test.junit.QuarkusTest
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import jakarta.inject.Inject
import jakarta.ws.rs.core.MediaType
import org.awaitility.Awaitility.await
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.Duration

/**
 * T-LIVE [P0] — Happy path through the full stack: REST POST → quarkus-rest-client SSE consumer
 * (Mutiny 2 in Quarkus 3) → FacebookCollector → event bus → ValidationService → REST GET, then
 * validate one BUY and confirm InventoryService records the sale. One test exercises R3, R4, R8
 * and the rest-* extension rename together.
 *
 * NB: the validation POST returns 204 — the JAX-RS handler has a Unit return type.
 */
@QuarkusTest
class LiveLifecycleIT {
    @Inject lateinit var inventoryService: InventoryService

    @AfterEach
    fun tearDown() {
        runCatching { When { delete("/live") } }
    }

    @Test
    fun testFullLiveLifecycle() {
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

        When { get("/live") } Then { statusCode(200); body(equalTo("123")) }

        await().atMost(Duration.ofSeconds(10)).until {
            When { get("/live/comments/validation/list") } Extract {
                jsonPath().getList<Any>("").isNotEmpty()
            }
        }

        val buyComment = When { get("/live/comments/validation/list") } Extract {
            jsonPath().getList<Map<String, Any>>("findAll { it.action == 'BUY' }").firstOrNull()
        }
        assertTrue(buyComment != null, "MockServer stream should have produced at least one BUY")
        val commentId = "${buyComment!!["commentId"]}_${buyComment["item"]}"

        Given {
            contentType(MediaType.APPLICATION_FORM_URLENCODED)
            formParam("action", ActionType.BUY.name)
        } When {
            post("/live/comments/validation/$commentId")
        } Then {
            statusCode(204)
        }

        await().atMost(Duration.ofSeconds(5)).until {
            inventoryService.salesHistory.isNotEmpty()
        }
    }
}
