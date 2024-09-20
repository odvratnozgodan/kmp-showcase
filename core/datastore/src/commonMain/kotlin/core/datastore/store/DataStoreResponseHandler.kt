package core.datastore.store

import core.common.base.model.CachingException
import core.common.base.usecese.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class DataStoreResponseHandler {
    suspend fun <T> safeCacheOperationCall(cacheOperation: suspend () -> T): DataResult<T> = withContext(Dispatchers.Default) {
        try {
            val response = cacheOperation()
            DataResult.Success(response)
        } catch (exception: Exception) {
            DataResult.Error(
                CachingException.GeneralError(message = exception.message)
            )
        }
    }
}
