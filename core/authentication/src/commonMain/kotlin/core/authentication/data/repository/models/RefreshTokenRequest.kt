package core.authentication.data.repository.models

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenRequest(val accessToken: String, val refreshToken: String)
