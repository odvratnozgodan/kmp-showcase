package core.authentication.data.repository.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationRequest(val username: String, val password: String)
