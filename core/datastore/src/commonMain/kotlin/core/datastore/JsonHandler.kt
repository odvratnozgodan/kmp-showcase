package core.datastore

import core.common.base.model.JsonException
import core.common.base.usecese.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

suspend inline fun <reified RM> String.safeFromJson(): DataResult<RM> = withContext(Dispatchers.Default) {
    try {
        DataResult.Success(Json.decodeFromString<RM>(this@safeFromJson))
    } catch (e: Exception) {
        DataResult.Error(
            JsonException.DecodingError(
                message = e.toString()
            )
        )
    }
}

suspend inline fun <reified RM> RM.safeToJson(): String = withContext(Dispatchers.Default) {
    Json.encodeToString(this@safeToJson)
}
