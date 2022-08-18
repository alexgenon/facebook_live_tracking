package be.aufildemescoutures.domain.core.LiveEvent

import kotlinx.datetime.Clock

data class LiveControl(
    val liveId: String = Clock.System.now().toString(),
    val fbVideoId: String = "",
    val providedInventorySize: Int = 100,
)
