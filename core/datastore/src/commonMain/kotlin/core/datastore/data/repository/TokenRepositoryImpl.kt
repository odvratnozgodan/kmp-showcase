package core.datastore.data.repository

import core.common.base.usecese.DataResult
import core.datastore.store.DataStoreManager

class TokenRepositoryImpl(private val dataStoreManager: DataStoreManager) : TokenRepository {

    companion object {
        const val USER_ACCESS_TOKEN = "USER_ACCESS_TOKEN"
        const val USER_REFRESH_ACCESS_TOKEN = "USER_REFRESH_ACCESS_TOKEN"
        const val FCM_TOKEN = "FCM_TOKEN"
        const val SELECTED_PROPERTY_ID = "SELECTED_PROPERTY_ID"
    }

    override suspend fun getAccessToken(): DataResult<String> = dataStoreManager.readStringOrDefault(
        USER_ACCESS_TOKEN,
        ""
    )

    override suspend fun setAccessToken(token: String): DataResult<Boolean> = dataStoreManager.saveString(USER_ACCESS_TOKEN, token)

    override suspend fun getRefreshToken(): DataResult<String> = dataStoreManager.readStringOrDefault(
        USER_REFRESH_ACCESS_TOKEN,
        ""
    )

    override suspend fun setRefreshToken(token: String): DataResult<Boolean> = dataStoreManager.saveString(USER_REFRESH_ACCESS_TOKEN, token)
}
