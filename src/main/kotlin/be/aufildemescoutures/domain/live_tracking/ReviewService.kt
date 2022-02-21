package be.aufildemescoutures.domain.live_tracking

import be.aufildemescoutures.domain.ActionType
import be.aufildemescoutures.domain.Comment
import io.quarkus.vertx.ConsumeEvent
import io.smallrye.mutiny.Multi
import io.vertx.mutiny.core.eventbus.EventBus
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@ApplicationScoped
class ReviewService {
    private val LOG = Logger.getLogger(javaClass)
    private val reviewList = mutableSetOf<Int>()
    private val questionList = mutableListOf<Comment>()
    private var streamOfReviewComments: Multi<Int> = Multi.createFrom().nothing()
    private var streamOfQuestionComments: Multi<Comment> = Multi.createFrom().nothing()

    @Inject
    @field:Default
    lateinit var bus: EventBus

    @ConsumeEvent(LiveEvent.reviewBusName)
    fun newReviewRequest(comment: Comment) {
        newToReview(comment)
    }

    fun trackReviewRequests(): Multi<Int> = this.streamOfReviewComments
    fun catchupWithReviewRequests():Set<Int> = this.reviewList
    fun itemReviewed(itemId: Int) {
        reviewList.remove(itemId)
    }

    fun questionAnswered(comment: Comment){
        val removeResult = questionList.remove(comment)
        LOG.debug("Removal of $comment returned $removeResult")
        LOG.debug("Now have ${questionList.size} pending questions")
    }

    private fun newToReview(comment: Comment){
        LOG.debug("Request to review $comment")
        reviewList.add(comment.item)
    }

    fun liveStarted(commentsForReview: Multi<Comment>) {
        this.streamOfReviewComments = commentsForReview
            .filter { it.action == ActionType.REVIEW }
            .map { comment ->
                newToReview(comment)
                comment.item
            }

        this.streamOfQuestionComments = commentsForReview
            .filter { it.action == ActionType.QUESTION }
            .map { comment->
                questionList.add(comment)
                comment
            }
    }
}