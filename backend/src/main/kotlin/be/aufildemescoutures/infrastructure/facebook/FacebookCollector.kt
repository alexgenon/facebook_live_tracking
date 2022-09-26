package be.aufildemescoutures.infrastructure.facebook

import be.aufildemescoutures.domain.live_tracking.core.comment.ActionType
import be.aufildemescoutures.domain.live_tracking.core.comment.Comment
import be.aufildemescoutures.domain.live_tracking.core.customer.CustomerId
import be.aufildemescoutures.domain.live_tracking.core.customer.FacebookUser
import be.aufildemescoutures.domain.live_tracking.core.customer.NoRecordedUser
import io.smallrye.mutiny.Multi
import kotlinx.datetime.Instant
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.logging.Logger
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FacebookCollector {
    @ConfigProperty(name = "comments.output_directory")
    internal lateinit var outputDirectory: String

    @ConfigProperty(name = "facebook.page_access_token")
    internal lateinit var token: String

    private val comment_rate = "ten_per_second"
    private val fields ="from{name,id},created_time,message"

    @Inject
    @RestClient
    lateinit var videoStream: VideoStream

    fun collectComments(video: String): Multi<List<Comment>> {
        val commentsWriter = File(outputDirectory, "$video.out").printWriter()
        val stream = videoStream
            .getComments(video, this.token, this.fields, this.comment_rate)
            .map {
                val comments = fromFacebook(it)
                LOG.debug("Comments extracted:\n${comments}")
                with(commentsWriter) { //TODO: persist this using Hibernate instead
                    println(Json.encodeToString(comments))
                    flush()
                }
                comments
            }
        return stream
    }

    companion object {
        val LOG = Logger.getLogger(FacebookCollector::class.java)
        val numberPattern = Regex("\\d+")
        val buyKeywords = setOf("prend", "achet", "achèt")
        val reviewKeywords = setOf("revoir")
        val questionKeywords = setOf("?")
        private fun JsonElement?.toCleanString():String = this.toString().trim('"')
        private fun String.toIso8601():String = this.toString()
            .replace(Regex("\\+(\\d{3})\\d"),"\\.$1Z")

        private fun containsAny(message: String, keywords: Collection<String>): Boolean =
            keywords.any { message.contains(it, true) }

        fun fromFacebook(data: String): List<Comment> {
            LOG.trace("About to parse $data")
            val jsonObject = Json
                .parseToJsonElement(data.replace(Regex("^data\\s*:\\s*"), ""))
                .jsonObject
            val user = jsonObject["from"]?.jsonObject
            val domainUser = (if (user != null) {
                FacebookUser(user["name"].toCleanString(), CustomerId( user["id"].toCleanString()))
            } else {
                NoRecordedUser
            })

            val commentId = jsonObject["id"].toCleanString()
            val timestamp = Instant.parse(jsonObject["created_time"].toCleanString().toIso8601())
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
