package be.aufildemescoutures.domain.live_tracking.core.customer

import kotlin.jvm.JvmInline

@kotlinx.serialization.Serializable
sealed class Customer {
    abstract val id:CustomerId
    abstract fun fullName():String
}

@JvmInline
@kotlinx.serialization.Serializable
value class CustomerId(val id:String)

@kotlinx.serialization.Serializable
object NoRecordedUser: Customer() {
    override val id: CustomerId = CustomerId("<No user>")
    override fun fullName(): String = "<No user>"
}

@kotlinx.serialization.Serializable
data class FacebookUser(val name: String, override val id: CustomerId): Customer() {
    override fun fullName() =name
}
