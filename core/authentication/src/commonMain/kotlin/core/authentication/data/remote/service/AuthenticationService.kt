package core.authentication.data.remote.service

import core.authentication.data.repository.models.AuthenticationRequest
import core.authentication.data.repository.models.RefreshTokenRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class AuthenticationService {

    fun login(request: AuthenticationRequest): HttpRequestBuilder.() -> Unit = {
        method = HttpMethod.Post
        url("auth/login")
        setBody(request)
    }

    fun refresh(request: RefreshTokenRequest): HttpRequestBuilder.() -> Unit = {
        method = HttpMethod.Post
        url("auth/refresh")
        setBody(request)
    }
}
