package be.aufildemescoutures.domain.live_tracking

import be.aufildemescoutures.domain.ActionType
import be.aufildemescoutures.domain.Comment

/**
 * Maps the different types of comment to the buses where the event needs to be published
 * Is a kind of routing based mostly on ActionType class
 */
data class LiveEvent(val comment: Comment) {
    companion object {
        const val reviewBusName = "COMMENT.REVIEW"
        const val buyBusName = "COMMENT.BUY"
        const val nonCuratedComments = "COMMENT.NON_CURATED"
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

