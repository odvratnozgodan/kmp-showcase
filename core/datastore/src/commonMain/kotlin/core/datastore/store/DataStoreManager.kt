package core.datastore.store

import core.common.base.usecese.DataResult

interface DataStoreManager {
    suspend fun saveBoolean(key: String, value: Boolean): DataResult<Boolean>
    suspend fun readBooleanOrDefault(key: String, defaultValue: Boolean): DataResult<Boolean>

    suspend fun saveString(key: String, value: String): DataResult<Boolean>
    suspend fun readStringOrDefault(key: String, defaultValue: String): DataResult<String>
    suspend fun removeString(key: String): DataResult<Boolean>
}
