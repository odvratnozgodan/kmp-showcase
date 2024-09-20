package core.authentication.data.remote.datasource

import core.authentication.data.remote.model.AuthenticationResult
import core.authentication.data.repository.models.AuthenticationRequest
import core.authentication.data.repository.models.RefreshTokenRequest
import core.common.base.usecese.DataResult

interface AuthenticationDataSource {
    suspend fun login(request: AuthenticationRequest): DataResult<AuthenticationResult>
    suspend fun refresh(request: RefreshTokenRequest): DataResult<AuthenticationResult>
}
