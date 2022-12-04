package be.aufildemescoutures.frontend.util

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Instant.toTimeStr(): String {
    /**
     * Initial approach was to use js 'padStart' but, for a reason I could not explain
     * String.adDynamic().padStart(2,'0') caused some problems.
     */
    fun Int.onTD(): String {
        val pad: String = if (this < 10) "0" else ""
        return "$pad$this"
    }

    val time = toLocalDateTime(TimeZone.currentSystemDefault())
    return "${time.hour.onTD()}:${time.minute.onTD()}:${time.second.onTD()}"
}
