package core.user.data.remote.service

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class UserService {

    fun getProfile(): HttpRequestBuilder.() -> Unit = {
        method = HttpMethod.Get
        url("auth/me")
    }
}
