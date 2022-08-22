package be.aufildemescoutures.domain.live_tracking

import be.aufildemescoutures.domain.core.ActionType
import be.aufildemescoutures.domain.core.Comment

typealias EventType=String
/**
 * Models event happening around a Comment
 * Also used to map the different types of comment to the buses where the event needs to be published
 * Is a kind of routing based mostly on ActionType class
 */
@kotlinx.serialization.Serializable
data class LiveEvent(val comment: Comment, val eventType:EventType ) {
    companion object {
        /*
        I intend to use those values as annotation arguments for @ConsumeEvent.
        Thus, I cannot use an enum as I need to do an <enum>.<value>.toString() which is not a compile time constant
         */
        const val reviewBusName = "COMMENT.REVIEW"
        const val buyBusName = "COMMENT.BUY"
        const val newComment = "COMMENT.NEW"
        const val commentToValidate = "COMMENT.TO_VALIDATE"
        const val commentValidated = "COMMENT.VALIDATED"
        const val controlBus = "LIVE.CONTROL"
        const val stopEvent = "STOP"
        fun busesInterestedIn(actionType: ActionType):Collection<String> =
            if(actionType==ActionType.BUY){
                setOf(buyBusName)
            } else if(actionType==ActionType.QUESTION || actionType == ActionType.REVIEW) {
                setOf(reviewBusName)
            } else{
                emptySet<String>()
            }
    }
}

