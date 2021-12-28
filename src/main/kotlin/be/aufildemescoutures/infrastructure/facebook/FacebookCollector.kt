package be.aufildemescoutures.infrastructure.facebook

import be.aufildemescoutures.domain.ActionType
import be.aufildemescoutures.domain.Comment
import be.aufildemescoutures.domain.CommentList
import be.aufildemescoutures.domain.FacebookUser
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.subscription.Cancellable
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
    private lateinit var outputDirectory: String
    private val comment_rate = "ten_per_second"
    private val fields = URLEncoder.encode("from{name,id},created_time,message", "UTF-8")

    @Inject
    @RestClient
    lateinit var videoStream: VideoStream

    val LOG = Logger.getLogger(FacebookCollector::class.java)

    fun collectComments(video: String, token: String): Multi<CommentList> {
        val commentsWriter= File(outputDirectory,"$video.out").printWriter()
        val stream= videoStream
            .getComments(video, token, this.fields, this.comment_rate)
            .map {
                LOG.debug("Message received: $it")
                val comments = fromFacebook(it)
                LOG.debug("Comments extracted:\n${comments}")
                with(commentsWriter){
                    println(comments)
                    flush()
                }
                comments
            }
        return stream
    }

    companion object {
        val facebookDatePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
        val numberPattern = Regex("\\d+")
        private fun JsonElement?.toCleanString() = this.toString().trim('"')

        fun fromFacebook(data: String): CommentList {
            val jsonObject = Json
                .parseToJsonElement(data.replace(Regex("^data\\s*:\\s*"), ""))
                .jsonObject
            val user = jsonObject["from"]!!.jsonObject
            val domainUser = FacebookUser(user["name"].toCleanString(), user["id"].toCleanString())
            val commentId = jsonObject["id"].toCleanString()
            val timestamp = LocalDateTime.parse(jsonObject["created_time"].toCleanString(), facebookDatePattern)
            val message = jsonObject["message"].toCleanString()
            val decision = (
                    if (message.contains("prend", true)) ActionType.BUY
                    else if (message.contains("combien", true)
                        || message.contains("dispo", true)
                    ) ActionType.QUESTION
                    else ActionType.REVIEW
                    )
            return numberPattern
                .findAll(message)
                .map { it.value.toInt() }
                .map { itemId -> Comment(commentId, domainUser, itemId, timestamp, message, decision) }
                .toList()
        }
    }
}