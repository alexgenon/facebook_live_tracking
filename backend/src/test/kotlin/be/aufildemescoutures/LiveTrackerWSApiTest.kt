package be.aufildemescoutures

import be.aufildemescoutures.domain.live_tracking.core.comment.ActionType
import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import be.aufildemescoutures.domain.live_tracking.core.customer.CustomerId
import be.aufildemescoutures.domain.live_tracking.core.customer.FacebookUser
import be.aufildemescoutures.domain.live_tracking.core.live_event.LiveEvent
import io.quarkus.test.common.http.TestHTTPResource
import io.quarkus.test.junit.QuarkusTest
import io.vertx.mutiny.core.eventbus.EventBus
import jakarta.inject.Inject
import jakarta.websocket.ClientEndpointConfig
import jakarta.websocket.ContainerProvider
import jakarta.websocket.Endpoint
import jakarta.websocket.EndpointConfig
import jakarta.websocket.MessageHandler
import jakarta.websocket.Session
import kotlinx.datetime.Clock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.net.URI
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.TimeUnit

/**
 * T-WS [P0] — Catches R3 (jakarta.websocket migration) and R8 (allOpen jakarta strings).
 * Order-independent: doesn't assume the WS is the only producer, just that a message
 * tagged with our unique commentId reaches the client after we publish it on the bus.
 */
@QuarkusTest
class LiveTrackerWSApiTest {
    @Inject
    lateinit var eventBus: EventBus

    @TestHTTPResource("/live/comments/validation")
    lateinit var endpoint: URI

    @Test
    fun `WS broadcasts a Comment published on commentToValidate to connected clients`() {
        val received = LinkedBlockingDeque<String>()
        val wsUri = URI("ws", null, endpoint.host, endpoint.port, endpoint.path, null, null)

        val session = ContainerProvider.getWebSocketContainer().connectToServer(
            object : Endpoint() {
                override fun onOpen(session: Session, config: EndpointConfig) {
                    session.addMessageHandler(String::class.java, MessageHandler.Whole { received.offer(it) })
                }
            },
            ClientEndpointConfig.Builder.create().build(),
            wsUri,
        )

        try {
            val uniqueId = "ws-${System.nanoTime()}"
            val comment = Comment(
                commentId = uniqueId,
                user = FacebookUser("Test User", CustomerId("u-1")),
                item = 5,
                timestamp = Clock.System.now(),
                fullComment = "je prends le 5",
                action = ActionType.BUY,
            )
            eventBus.publish(LiveEvent.commentToValidate, comment)

            val deadline = System.currentTimeMillis() + 10_000
            var matched: LiveEvent? = null
            while (System.currentTimeMillis() < deadline && matched == null) {
                val msg = received.poll(500, TimeUnit.MILLISECONDS) ?: continue
                val events: List<LiveEvent> = runCatching { Json.decodeFromString<List<LiveEvent>>(msg) }.getOrDefault(emptyList())
                matched = events.firstOrNull { ev ->
                    ev.eventType == LiveEvent.commentToValidate &&
                        runCatching { ev.comment().commentId == uniqueId }.getOrDefault(false)
                }
            }
            assertNotNull(matched, "Did not receive a WS frame containing our published comment within 10s")
            assertEquals(LiveEvent.commentToValidate, matched!!.eventType)
            assertEquals(uniqueId, matched.comment().commentId)
        } finally {
            session.close()
        }
    }
}
