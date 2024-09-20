package core.authentication.data.remote.datasource

import core.authentication.data.remote.model.AuthenticationResult
import core.authentication.data.remote.service.AuthenticationService
import core.authentication.data.repository.models.AuthenticationRequest
import core.authentication.data.repository.models.RefreshTokenRequest
import core.common.base.usecese.DataResult
import core.network.safeApiCall
import io.ktor.client.HttpClient

class AuthenticationDataSourceImpl(private val httpClient: HttpClient, private val authenticationService: AuthenticationService) :
    AuthenticationDataSource {

    override suspend fun login(request: AuthenticationRequest): DataResult<AuthenticationResult> =
        httpClient.safeApiCall<AuthenticationResult>(
            authenticationService.login(request)
        )

    override suspend fun refresh(request: RefreshTokenRequest): DataResult<AuthenticationResult> =
        httpClient.safeApiCall<AuthenticationResult>(
            authenticationService.refresh(request)
        )
}
