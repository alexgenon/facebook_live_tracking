package be.aufildemescoutures.domain.live_tracking.core.comment

@kotlinx.serialization.Serializable
sealed class ContestManagement() {

    @kotlinx.serialization.Serializable
    object NoContest : ContestManagement() {
    }

    @kotlinx.serialization.Serializable
    class Contest(val keyword: String) : ContestManagement() {
        private val normalizedKeyword: String = keyword.trim().lowercase()
        private val isNumber: Boolean = numberRegex.matches(normalizedKeyword)

        fun newCommentForContest(comment: Comment): CommentForContest {
            val score = if (isNumber) {
                if (comment.fullComment.trim() == normalizedKeyword) 1f else 0f
            } else {
                // Later, a word distance algorithm will be considered to take into account wording issues
                if (comment.fullComment.lowercase().contains(normalizedKeyword)) 1f else 0f
            }
            return CommentForContest(comment, this, score)
        }

        companion object {
            private val numberRegex = Regex("\\d+")
        }
    }
}

@kotlinx.serialization.Serializable
data class CommentForContest(val comment: Comment,val contest: ContestManagement.Contest, val score:Float){
    val isAMatch:Boolean = (score >= 0.9f)
}
