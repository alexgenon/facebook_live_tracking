package be.aufildemescoutures.domain.live_tracking

import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import be.aufildemescoutures.domain.live_tracking.core.live_event.LiveEvent
import be.aufildemescoutures.domain.live_tracking.validation.ValidationService
import io.quarkus.vertx.ConsumeEvent
import io.vertx.mutiny.core.eventbus.EventBus
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.websocket.*
import javax.websocket.server.ServerEndpoint


@ServerEndpoint("/live/comments/validation")
@ApplicationScoped
class LiveTrackerWSApi {
    private val LOG = Logger.getLogger(javaClass)

    @Inject
    @field:Default
    lateinit var validationService: ValidationService

    @Inject
    lateinit var commmentBus: EventBus;

    private val sessions = mutableListOf<Session>()
    private fun liveEventToJson(liveEvents: List<LiveEvent>): String = Json.encodeToString(liveEvents)
    private fun liveEventFromJson(liveEvents: String):LiveEvent = Json.decodeFromString(liveEvents)

    @OnOpen
    fun onOpen(session: Session) {
        LOG.info("New session added: ${session.requestURI}")
        sessions.add(session)
        // Allow the new session to catch up with the pending comments.
        session.asyncRemote.sendObject(liveEventToJson(validationService.allPendingComments().map { LiveEvent.build(it,LiveEvent.commentToValidate)}))
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
    fun onMessage(message: String, session: Session) {
        LOG.debug("Message received: $message")
        val event = liveEventFromJson(message)
        this.commmentBus.publish(event.eventType,event.comment())
        sessions // Tell everyone about this validated comment
            .filterNot{ it == session} // Exclude the sender as he already knows about it
            .forEach { s -> s.asyncRemote.sendObject(liveEventToJson(listOf(event)))
                { checkForError(s,it)} }
    }

    @ConsumeEvent(LiveEvent.commentToValidate)
    fun newComment(comment: Comment) {
        LOG.trace("Sending message $comment")
        publishToAllWS(listOf(LiveEvent.build(comment, LiveEvent.commentToValidate)))
    }

    private fun publishToAllWS(liveEvents: List<LiveEvent>,filteredSessions:Set<Session>? = null) {
        val liveEventAsJson = liveEventToJson(liveEvents)
        // if no filteredSessions list is provided, defaults to all sessions
        (filteredSessions?:sessions).forEach { s ->
            s.asyncRemote.sendObject(liveEventAsJson)
            { checkForError(s, it) }
        }
    }

    @ConsumeEvent(LiveEvent.contestSwitch)
    fun contestSwitch(liveEvent: LiveEvent) {
        LOG.debug("Contest starting! : ${liveEvent.payload}")
        publishToAllWS(listOf(liveEvent))
    }

    @ConsumeEvent(LiveEvent.contestComment)
    fun newCommentForContest(liveEvent: LiveEvent){
        LOG.trace("New comment for contest ${liveEvent.payload}")
        publishToAllWS(listOf(liveEvent))
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
