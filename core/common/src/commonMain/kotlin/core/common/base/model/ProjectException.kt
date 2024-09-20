package core.common.base.model

sealed class ProjectException(override val message: String? = null) : Exception()

sealed class GeneralException(override val message: String?) : ProjectException(message = message) {

    class GeneralError(message: String? = null) : GeneralException(message = message ?: "Something went wrong")
}

sealed class NetworkException(override val message: String?) : ProjectException(message = message) {
    class RequestError(val httpStatusCode: String, message: String? = null) : NetworkException(message = message ?: "Something went wrong")

    class ResponseDataError(message: String? = null) : NetworkException(message = message ?: "Something went wrong")

    class GeneralError(message: String? = null) : NetworkException(message = message ?: "Something went wrong")
}

sealed class CachingException(override val message: String?) : ProjectException(message = message) {
    class GeneralError(message: String? = null) :
        CachingException(message = message ?: "Something went wrong while caching the data")
}

sealed class JsonException(override val message: String?) : ProjectException(message = message) {
    class DecodingError(message: String) : JsonException(message = "Something went wrong while decoding the JSON string: $message")
}

sealed class AuthenticationException(override val message: String?) : ProjectException(message = message) {
    class Unauthorized(message: String? = null) : AuthenticationException(message = message)
    class BadRequest(message: String? = null) : AuthenticationException(message = message)
}

sealed class ValidationException(override val message: String? = null) : ProjectException(message = message) {
    data object EmptyEmail : ValidationException(message = null)
    data object InValidEmail : ValidationException(message = null)
}
