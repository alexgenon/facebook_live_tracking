package be.aufildemescoutures.domain.live_tracking.core.live_event

import kotlinx.datetime.Clock

data class LiveControl(
    val liveId: String = Clock.System.now().toString(),
    val fbVideoId: String = "",
    val providedInventorySize: Int = 100,
    val contestKeyword:String = ""
)
