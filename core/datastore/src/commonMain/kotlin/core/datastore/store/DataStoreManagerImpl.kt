package core.datastore.store

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import core.common.base.usecese.DataResult

class DataStoreManagerImpl(private val storage: Settings) :
    DataStoreResponseHandler(),
    DataStoreManager {

    override suspend fun saveBoolean(key: String, value: Boolean): DataResult<Boolean> {
        val result = safeCacheOperationCall {
            storage[key] = value
        }
        return when (result) {
            is DataResult.Success -> DataResult.Success(true)
            is DataResult.Error -> result
        }
    }

    override suspend fun readBooleanOrDefault(key: String, defaultValue: Boolean): DataResult<Boolean> = safeCacheOperationCall {
        storage[key, defaultValue]
    }

    override suspend fun saveString(key: String, value: String): DataResult<Boolean> {
        val result = safeCacheOperationCall {
            storage[key] = value
        }
        return when (result) {
            is DataResult.Success -> DataResult.Success(true)
            is DataResult.Error -> result
        }
    }

    override suspend fun readStringOrDefault(key: String, defaultValue: String): DataResult<String> = safeCacheOperationCall {
        storage[key, defaultValue]
    }

    override suspend fun removeString(key: String): DataResult<Boolean> {
        val result = safeCacheOperationCall {
            storage.remove(key)
        }

        return when (result) {
            is DataResult.Success -> DataResult.Success(true)
            is DataResult.Error -> result
        }
    }
}
