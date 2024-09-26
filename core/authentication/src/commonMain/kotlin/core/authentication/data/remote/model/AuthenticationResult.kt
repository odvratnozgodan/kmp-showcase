package core.authentication.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationResult(
    @SerialName("accessToken")
    val token: String,
    val refreshToken: String,
)
