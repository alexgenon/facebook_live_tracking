package be.aufildemescoutures.frontend

import be.aufildemescoutures.domain.live_tracking.core.comment.ContestManagement
import kotlin.js.Date

data class ServerStatus(val status: ServerStatusEnum, val text: String,
                        val contest: ContestManagement = ContestManagement.NoContest,
                        val logs: List<String> = emptyList()) {
    fun connectionActive(): Boolean = (status != ServerStatusEnum.FETCHING)
    fun addLog(text: String) = this.copy(logs = logs + ("${timeAsString()}: $text"))
    private fun timeAsString(): String {
        val now = Date()
        return "${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}"
    }
    fun contestStarted(contest: ContestManagement) = this.copy(contest=contest)
    fun contestOngoing() = this.contest != ContestManagement.NoContest
}

enum class ServerStatusEnum { FETCHING, LIVE, NO_LIVE }
