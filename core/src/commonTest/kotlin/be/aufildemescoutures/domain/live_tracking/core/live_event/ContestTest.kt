package be.aufildemescoutures.domain.live_tracking.core.live_event

import be.aufildemescoutures.domain.live_tracking.core.comment.ContestManagement
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlin.test.Test
import kotlin.test.assertEquals

class ContestTest {
    @Test
    fun testNoContest(){
        val contest:ContestManagement = ContestManagement.NoContest
        val contestAsJson = Json.encodeToString(contest)
        val contestFromJson = Json.decodeFromString<ContestManagement>(contestAsJson)
        assertEquals(contest,contestFromJson)
    }
}
