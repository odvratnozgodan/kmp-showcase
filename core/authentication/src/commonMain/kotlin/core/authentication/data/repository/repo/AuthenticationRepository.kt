package core.authentication.data.repository.repo

import core.authentication.data.remote.model.AuthenticationResult
import core.common.base.usecese.DataResult

interface AuthenticationRepository {

    suspend fun login(username: String, password: String): DataResult<AuthenticationResult>

    suspend fun refreshToken(accessToken: String, refreshToken: String): DataResult<AuthenticationResult>
}
