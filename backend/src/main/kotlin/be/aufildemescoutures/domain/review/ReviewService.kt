package be.aufildemescoutures.domain.review

import be.aufildemescoutures.domain.core.ActionType
import be.aufildemescoutures.domain.core.Comment
import be.aufildemescoutures.domain.live_tracking.LiveEvent
import io.quarkus.vertx.ConsumeEvent
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.operators.multi.processors.UnicastProcessor
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ReviewService {
    private val LOG = Logger.getLogger(javaClass)
    private val reviewList = mutableSetOf<Int>()
    private val questionsList = mutableListOf<Question>()

    private var questionsUnicast = UnicastProcessor.create<Question>()
    private var reviewsUnicast = UnicastProcessor.create<Int>()

    @ConsumeEvent(LiveEvent.reviewBusName)
    fun newRequest(comment: Comment) {
        if(comment.action==ActionType.REVIEW) {
            newToReview(comment)
        } else if(comment.action==ActionType.QUESTION){
            newQuestion(comment)
        }
    }

    private fun newToReview(comment: Comment){
        LOG.debug("Sending ${comment.item} as new item to review")
        reviewList.add(comment.item)
        reviewsUnicast.onNext(comment.item)
    }
    fun reviewStream ():Multi<Int>{
        if(reviewsUnicast.hasSubscriber()){
            reviewsUnicast.onComplete()
            reviewsUnicast = UnicastProcessor.create<Int>()
        }
        reviewList.forEach {
            LOG.debug("Sending $it as item to review")
            reviewsUnicast.onNext(it)
        }
        return reviewsUnicast
    }
    fun itemReviewed(itemId: Int) {
        reviewList.remove(itemId)
        LOG.debug("Item $itemId reviewed\n" +
                "Now have ${reviewList.size} items to review ")
    }

    private fun newQuestion(comment: Comment) {
        val question=Question(comment)
        LOG.debug("Question ${question.getQuestionText()} sent as new question")
        questionsList.add(question)
        questionsUnicast.onNext(question)
    }

    fun questionsStream ():Multi<Question>{
        if(questionsUnicast.hasSubscriber()){
            questionsUnicast.onComplete()
            questionsUnicast = UnicastProcessor.create<Question>()
        }
        questionsList.forEach {
            LOG.debug("Question ${it.getId()} sent as not yet answered")
            questionsUnicast.onNext(it)
        }
        return questionsUnicast
    }
    fun questionAnswered(questionId:String){
        val removeResult = questionsList.removeIf{it.getId().equals(questionId)}
        LOG.debug("Removal of $questionId returned $removeResult\n" +
                "Now have ${questionsList.size} pending questions")
    }
}
