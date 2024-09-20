package core.authentication.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationResult(val token: String, val refreshToken: String)
