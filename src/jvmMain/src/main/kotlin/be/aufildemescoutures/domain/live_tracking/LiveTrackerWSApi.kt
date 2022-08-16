package be.aufildemescoutures.domain.live_tracking

import be.aufildemescoutures.domain.Comment
import be.aufildemescoutures.domain.live_tracking.validation.ValidationService
import com.fasterxml.jackson.databind.ObjectMapper
import io.quarkus.vertx.ConsumeEvent
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.websocket.*
import javax.websocket.server.ServerEndpoint


@ServerEndpoint("/api/comments/validation")
@ApplicationScoped
class LiveTrackerWSApi {
    private val LOG = Logger.getLogger(javaClass)

    @Inject
    @field:Default
    lateinit var validationService: ValidationService

    @Inject
    @field:Default
    lateinit var objectMapper: ObjectMapper

    private val sessions = mutableListOf<Session>()
    private fun liveEventToJson(liveEvents: List<LiveEvent>): String = objectMapper.writeValueAsString(liveEvents)

    @OnOpen
    fun onOpen(session: Session) {
        LOG.info("New session added: ${session.requestURI}")
        sessions.add(session)
        session.asyncRemote.sendObject(
            liveEventToJson(validationService.allPendingComments().map { LiveEvent(it,LiveEvent.commentToValidate)})
        )
        { checkForError(session,it) }
    }

    @OnClose
    fun onClose(session: Session) {
        LOG.info("Session disconnected ${session.requestURI}")
        sessions.remove(session)
    }

    @OnError
    fun onError(session: Session, throwable: Throwable) {
        LOG.error("Error with session $session", throwable)
        sessions.remove(session)
    }

    @OnMessage
    fun onMessage(message: String) {
        LOG.debug("Message received: $message")
    }

    @ConsumeEvent(LiveEvent.commentToValidate)
    fun newComment(comment: Comment) {
        LOG.debug("Sending message ${comment.toString()}")
        sessions.forEach {
                s -> s.asyncRemote.sendObject(liveEventToJson(listOf(LiveEvent(comment,LiveEvent.commentToValidate))))
                { checkForError(s,it)}
        }
    }

    private fun checkForError(session:Session, result: SendResult) {
        if(!result.isOK) {
            LOG.error(
                "Cannot send message to remote session ${session.requestURI}: ${result.exception}",
                result.exception
            )
        }
    }
}
