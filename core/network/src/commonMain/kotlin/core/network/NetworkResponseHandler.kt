package core.network

import core.common.base.model.AuthenticationException
import core.common.base.model.NetworkException
import core.common.base.model.ProjectException
import core.common.base.usecese.DataResult
import core.common.logger.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

suspend inline fun <reified RM> HttpClient.safeApiCall(noinline block: HttpRequestBuilder.() -> Unit): DataResult<RM> =
    withContext(Dispatchers.Default) {
        try {
            val response = this@safeApiCall.request { block() }
            when (response.status) {
                HttpStatusCode.OK -> {
                    val data = response.body<RM>()
                    DataResult.Success(data = data)
                }

                else -> {
                    DataResult.Error(convertErrorBody(response))
                }
            }
        } catch (exception: Exception) {
            Logger.e("$exception")
            DataResult.Error(
                NetworkException.GeneralError(
                    message = exception.message
                )
            )
        }
    }

@Serializable
private data class Message(val message: String)

suspend fun convertErrorBody(response: HttpResponse): ProjectException {
    val message = try {
        response.body<Message>().message
    } catch (e: Exception) {
        null
    }
    return when (response.status) {
        HttpStatusCode.Unauthorized -> AuthenticationException.Unauthorized(message)
        HttpStatusCode.BadRequest -> AuthenticationException.BadRequest(message)

        else -> {
            NetworkException.RequestError(
                httpStatusCode = response.status.value.toString(),
                message = response.status.description
            )
        }
    }
}
