package core.user.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val id: Int,
    val image: String,
    val username: String,
)
