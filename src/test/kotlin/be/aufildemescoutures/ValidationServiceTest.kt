package be.aufildemescoutures

import be.aufildemescoutures.mock.MockServer
import be.aufildemescoutures.domain.Comment
import be.aufildemescoutures.domain.live_tracking.LiveEvent
import be.aufildemescoutures.domain.live_tracking.validation.ValidationService
import be.aufildemescoutures.infrastructure.facebook.FacebookCollector
import be.aufildemescoutures.infrastructure.facebook.VideoStream
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.vertx.mutiny.core.eventbus.EventBus
import org.jboss.logging.Logger
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import javax.enterprise.inject.Default
import javax.inject.Inject

@QuarkusTest
class ValidationServiceTest {
    val LOG = Logger.getLogger(VideoStream::class.java)

    @Inject
    @field:Default
    internal lateinit var validationService: ValidationService

    @Inject
    lateinit var commmentBus: EventBus;

    @Test
    @Disabled
    fun testGetCommentsToValidate(){
        MockServer.comment_stream.split("\n")
            .flatMap{FacebookCollector.fromFacebook(it)}
            .forEach{commmentBus.publish(LiveEvent.nonCuratedComments,it)}

        commmentBus.publish(LiveEvent.controlBus,LiveEvent.stopEvent)

        lateinit var commentsList:List<Comment>
        try {
            commentsList = validationService.streamOfCommentsPendingValidation()
                .onItem().invoke { comment -> LOG.debug("Comment to validate: $comment") }
                .collect().asList().await().indefinitely()
        } catch (e:io.smallrye.mutiny.TimeoutException) {
            // Do nothing, this is expected
        }
        assertAll("Get the expected list of comments to validate",
            { assertEquals(6,commentsList.count()) }
        )
    }

    companion object {
        private val video = "1234"
    }
}
