package be.aufildemescoutures.domain.live_tracking

import be.aufildemescoutures.domain.Comment

data class LiveEvent(val comment: Comment) {
    companion object {
        const val reviewBusName = "COMMENT.REVIEW"
        const val questionBusName = "COMMENT.QUESTION"
        const val buyBusName = "COMMENT.BUY"
        const val nonCuratedComments = "COMMENT.NON_CURATED"
        const val controlBus = "LIVE.CONTROL"
        const val stopEvent = "STOP"
    }
}

