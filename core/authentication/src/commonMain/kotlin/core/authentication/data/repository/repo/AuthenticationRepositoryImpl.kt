package core.authentication.data.repository.repo

import core.authentication.data.remote.datasource.AuthenticationDataSource
import core.authentication.data.remote.model.AuthenticationResult
import core.authentication.data.repository.models.AuthenticationRequest
import core.authentication.data.repository.models.RefreshTokenRequest
import core.common.base.usecese.DataResult

class AuthenticationRepositoryImpl(private val authenticationDatasource: AuthenticationDataSource) : AuthenticationRepository {

    override suspend fun login(username: String, password: String): DataResult<AuthenticationResult> =
        authenticationDatasource.login(AuthenticationRequest(username, password))

    override suspend fun refreshToken(accessToken: String, refreshToken: String): DataResult<AuthenticationResult> =
        authenticationDatasource.refresh(RefreshTokenRequest(accessToken, refreshToken))
}
