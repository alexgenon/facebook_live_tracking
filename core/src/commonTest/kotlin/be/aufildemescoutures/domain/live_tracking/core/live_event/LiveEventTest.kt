package be.aufildemescoutures.domain.live_tracking.core.live_event

import be.aufildemescoutures.domain.live_tracking.core.comment.ActionType
import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import be.aufildemescoutures.domain.live_tracking.core.comment.CommentForContest
import be.aufildemescoutures.domain.live_tracking.core.comment.Contest
import be.aufildemescoutures.domain.live_tracking.core.customer.NoRecordedUser
import kotlinx.datetime.Clock
import kotlin.test.assertTrue
import kotlin.test.assertFails
import kotlin.test.Test

class LiveEventTest {
    @Test
    fun testCommentPayload(){
        val c = Comment("123",NoRecordedUser,2, Clock.System.now(),"",ActionType.BUY)
        val le = LiveEvent.build(c,LiveEvent.commentValidated)
        assertTrue { le.payload is CommentPayload }
    }
    @Test
    fun testCommentForContestPayload(){
        val c = Comment("123",NoRecordedUser,2, Clock.System.now(),"",ActionType.BUY)
        val c4c = CommentForContest(c, Contest.NONE,0.3f)
        val le = LiveEvent.build(c4c,LiveEvent.contestComment)
        assertTrue { le.payload is CommentForContestPayload }
    }
    @Test
    fun testContestPayload(){
        val c = Contest("coucou")
        val le = LiveEvent.build(c,LiveEvent.contestSwitch)
        assertTrue { le.payload is ContestPayload }
    }
    @Test
    fun testUnsupportedPayload(){
        assertFails {
            val someObject = Clock.System.now()
            LiveEvent.build(someObject,LiveEvent.commentValidated)
        }
    }
}
