package core.datastore.data.repository

import core.common.base.usecese.DataResult

interface TokenRepository {
    suspend fun getAccessToken(): DataResult<String>
    suspend fun setAccessToken(token: String): DataResult<Boolean>
    suspend fun getRefreshToken(): DataResult<String>
    suspend fun setRefreshToken(token: String): DataResult<Boolean>
}
