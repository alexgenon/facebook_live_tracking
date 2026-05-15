package be.aufildemescoutures

import be.aufildemescoutures.domain.live_tracking.core.comment.ActionType
import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import be.aufildemescoutures.domain.live_tracking.core.customer.CustomerId
import be.aufildemescoutures.domain.live_tracking.core.customer.FacebookUser
import be.aufildemescoutures.domain.live_tracking.core.live_event.LiveEvent
import be.aufildemescoutures.domain.live_tracking.validation.ValidationService
import be.aufildemescoutures.domain.sales.InventoryService
import io.quarkus.test.junit.QuarkusTest
import io.vertx.mutiny.core.eventbus.EventBus
import jakarta.inject.Inject
import kotlinx.datetime.Clock
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Duration

/**
 * T-EVT [P0] — Verifies that @ConsumeEvent wiring still routes messages after the Quarkus 3
 * upgrade. Each test publishes on an address used in production and asserts the downstream
 * service reacted. If the Vert.x event bus wiring or jakarta CDI proxies were broken,
 * no service would fire and these assertions would time out.
 */
@QuarkusTest
class EventBusFlowTest {
    @Inject lateinit var eventBus: EventBus
    @Inject lateinit var inventoryService: InventoryService
    @Inject lateinit var validationService: ValidationService

    @BeforeEach
    fun setUp() {
        inventoryService.activeLive = InventoryService.createBasicInventory(20)
        inventoryService.salesHistory.clear()
    }

    private fun comment(item: Int, action: ActionType, suffix: String = "") = Comment(
        commentId = "evt-test-$item$suffix",
        user = FacebookUser("Tester $item", CustomerId("u-$item")),
        item = item,
        timestamp = Clock.System.now(),
        fullComment = "test comment for item $item",
        action = action,
    )

    @Test
    fun `Comment published on buyBusName ends up in inventory salesHistory`() {
        eventBus.publish(LiveEvent.buyBusName, comment(item = 3, action = ActionType.BUY))

        await().atMost(Duration.ofSeconds(5)).until {
            inventoryService.salesHistory.any { it.sku.id == 3 }
        }
    }

    @Test
    fun `Comment published on newComment goes through ValidationService and becomes a pending comment`() {
        val c = comment(item = 7, action = ActionType.BUY, suffix = "-pending")
        eventBus.publish(LiveEvent.newComment, c)

        await().atMost(Duration.ofSeconds(5)).until {
            validationService.allPendingComments().any { it.commentId == c.commentId }
        }
        assertTrue(validationService.allPendingComments().any { it.item == 7 })
    }
}
