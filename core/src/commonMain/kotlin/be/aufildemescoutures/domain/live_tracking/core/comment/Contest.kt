package be.aufildemescoutures.domain.live_tracking.core.comment

@kotlinx.serialization.Serializable
class Contest(val keyword:String) {
    val isNumber:Boolean by lazy { numberRegex.matches(keyword) }
    fun newCommentForContest(comment: Comment):CommentForContest{
        val score = if(isNumber){
            if (comment.fullComment.trim() == keyword)  1f else 0f
        } else {
            // Later, more word distance algorithm will be considered to take into account wording issues
            if (comment.fullComment.lowercase().contains(keyword.lowercase())) 1f else 0f
        }
        return CommentForContest(comment, this, score)
    }
    companion object{
        val NONE= Contest(keyword = "")
        val numberRegex = Regex("\\d+")
    }
}

@kotlinx.serialization.Serializable
data class CommentForContest(val comment: Comment,val contest: Contest, val score:Float){
    val isAMatch:Boolean = (score >= 0.9f)
}
