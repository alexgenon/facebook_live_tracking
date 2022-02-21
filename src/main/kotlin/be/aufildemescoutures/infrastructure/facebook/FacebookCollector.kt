package be.aufildemescoutures.infrastructure.facebook

import be.aufildemescoutures.domain.ActionType
import be.aufildemescoutures.domain.Comment
import be.aufildemescoutures.domain.CommentList
import be.aufildemescoutures.domain.FacebookUser
import io.smallrye.mutiny.Multi
import io.vertx.core.eventbus.EventBus
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.logging.Logger
import java.io.File
import java.net.URLEncoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FacebookCollector {
    @ConfigProperty(name = "comments.output_directory")
    internal lateinit var outputDirectory: String

    @ConfigProperty(name = "facebook.page_access_token")
    internal lateinit var token: String

    private val comment_rate = "ten_per_second"
    private val fields = URLEncoder.encode("from{name,id},created_time,message", "UTF-8")

    @Inject
    lateinit var eventBus: EventBus

    @Inject
    @RestClient
    lateinit var videoStream: VideoStream

    fun collectComments(video: String): Multi<CommentList> {
        val commentsWriter = File(outputDirectory, "$video.out").printWriter()
        val stream = videoStream
            .getComments(video, this.token, this.fields, this.comment_rate)
            .map {
                val comments = fromFacebook(it)
                LOG.debug("Comments extracted:\n${comments}")
                with(commentsWriter) {
                    println(comments)
                    flush()
                }
                comments
            }
        return stream
    }

    companion object {
        val LOG = Logger.getLogger(javaClass)
        val facebookDatePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
        val numberPattern = Regex("\\d+")
        val buyKeywords = setOf("prend", "achete", "achète")
        val reviewKeywords = setOf("revoir")
        val questionKeywords = setOf("?")
        private fun JsonElement?.toCleanString() = this.toString().trim('"')

        private fun containsAny(message: String, keywords: Collection<String>): Boolean =
            keywords.any { message.contains(it, true) }

        fun fromFacebook(data: String): CommentList {
            LOG.trace("About to parse $data")
            val jsonObject = Json
                .parseToJsonElement(data.replace(Regex("^data\\s*:\\s*"), ""))
                .jsonObject
            val user = jsonObject["from"]?.jsonObject
            val domainUser = (if (user != null) {
                FacebookUser(user.get("name").toCleanString(), user.get("id").toCleanString())
            } else {
                FacebookUser.NoRecordedUser
            })

            val commentId = jsonObject["id"].toCleanString()
            val timestamp = LocalDateTime.parse(jsonObject["created_time"].toCleanString(), facebookDatePattern)
            val message = jsonObject["message"].toCleanString()
            val decision = (
                    if (containsAny(message, buyKeywords)) ActionType.BUY
                    else if (containsAny(message, reviewKeywords)) ActionType.REVIEW
                    else if (containsAny(message, questionKeywords)) ActionType.QUESTION
                    else ActionType.NOTHING
                    )

            var comments= numberPattern
                .findAll(message)
                .map{it.value}
                .toList()

            if(comments.size<=0){
                comments = listOf("-1")
            }

            return comments.map {
                    try {
                        it.toInt()
                    } catch (numberException: NumberFormatException){
                        LOG.warn("found a number that cannot be parsed $it",numberException)
                        -1
                    }
                }
                .map { itemId -> Comment(commentId, domainUser, itemId, timestamp, message, decision) }
        }
    }
}