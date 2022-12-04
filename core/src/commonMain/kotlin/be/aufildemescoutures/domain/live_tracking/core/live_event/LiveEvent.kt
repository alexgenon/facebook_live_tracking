package be.aufildemescoutures.domain.live_tracking.core.live_event

import be.aufildemescoutures.domain.live_tracking.core.comment.ActionType
import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import be.aufildemescoutures.domain.live_tracking.core.comment.CommentForContest
import be.aufildemescoutures.domain.live_tracking.core.comment.ContestManagement

typealias EventType=String

@kotlinx.serialization.Serializable
sealed interface EventPayload
@kotlinx.serialization.Serializable
class CommentPayload(val comment: Comment): EventPayload
@kotlinx.serialization.Serializable
class CommentForContestPayload(val commentForContest: CommentForContest):EventPayload
@kotlinx.serialization.Serializable
class ContestPayload(val contest: ContestManagement):EventPayload

/**
 * Models event happening during a live, payload is the actual content of the event (normal comment,
 * comment in scope of a contest, or contest 'control' message)
 */
@kotlinx.serialization.Serializable
data class LiveEvent(val payload: EventPayload, val eventType:EventType ) {
    fun comment():Comment = when(payload) {
        is CommentPayload -> payload.comment
        else -> {throw UnsupportedOperationException("Payload $payload is not a comment")}
    }
    fun commentForContest():CommentForContest = when (payload){
        is CommentForContestPayload -> payload.commentForContest
        else -> {throw UnsupportedOperationException("Payload $payload is not a CommentForContest")}
    }
    fun contest(): ContestManagement = when(payload){
        is ContestPayload -> payload.contest
        else -> {throw UnsupportedOperationException("Payload $payload is not a Contest")}
    }

    companion object {
        /*
        I intend to use those values as annotation arguments for @ConsumeEvent.
        Thus, I cannot use an enum as I need to do an <enum>.<value>.toString() which is not a compile-time constant
        and then is not accepted as an annotation argument
         */
        const val reviewBusName = "COMMENT.REVIEW"
        const val buyBusName  = "COMMENT.BUY"
        const val newComment = "COMMENT.NEW"
        const val commentToValidate = "COMMENT.TO_VALIDATE"
        const val commentValidated = "COMMENT.VALIDATED"
        const val controlBus = "LIVE.CONTROL"
        const val contestSwitch = "CONTEST.SWITCH"
        const val contestComment = "CONTEST.COMMENT"
        const val stopEvent = "STOP"

        /**  This function maps the different types of comment to the buses where the event needs to be published
         *  (kind of routing based mostly on ActionType class)
         */
        fun busesInterestedIn(actionType: ActionType):Collection<String> =
            when (actionType) {
                ActionType.BUY -> {
                    setOf(buyBusName)
                }
                ActionType.QUESTION, ActionType.REVIEW -> {
                    setOf(reviewBusName)
                }
                else -> {
                    emptySet<String>()
                }
            }

        fun build(rawPayload: Any, eventType: EventType):LiveEvent {
            val payload = when (rawPayload) {
                is Comment -> CommentPayload(rawPayload)
                is CommentForContest -> CommentForContestPayload(rawPayload)
                is ContestManagement -> ContestPayload(rawPayload)
                else -> {
                    throw UnsupportedOperationException("Unsupported payload of type ${rawPayload::class.simpleName}: $rawPayload")
                }
            }
            return LiveEvent(payload, eventType)
        }
    }
}

