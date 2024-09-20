package core.authentication.domain.usecase

import core.authentication.data.remote.model.AuthenticationResult
import core.authentication.data.repository.repo.AuthenticationRepository
import core.common.base.usecese.DataResult
import core.common.base.usecese.MainEventsUseCase
import core.common.extensions.asFlow
import core.common.extensions.doOnResultSuccess
import core.common.extensions.ifNullOrBlank
import core.datastore.domain.usecase.GetAccessToken
import core.datastore.domain.usecase.GetRefreshToken
import core.datastore.domain.usecase.SetAccessToken
import core.datastore.domain.usecase.SetRefreshToken
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

class RefreshTokenAuthenticator(
    private val mainEventsUseCase: MainEventsUseCase,
    private val authenticationRepository: AuthenticationRepository,
    private val getAccessToken: GetAccessToken,
    private val setAccessToken: SetAccessToken,
    private val getRefreshToken: GetRefreshToken,
    private val setRefreshToken: SetRefreshToken,
) {
    suspend operator fun invoke(): DataResult<AuthenticationResult> {
        val accessToken = getAccessToken.invoke().ifNullOrBlank { "" }
        val refreshToken = getRefreshToken.invoke().ifNullOrBlank { "" }
        return authenticationRepository.refreshToken(accessToken, refreshToken).asFlow()
            .doOnResultSuccess {
                setAccessToken(it.data.token)
            }
            .doOnResultSuccess {
                setRefreshToken(it.data.refreshToken)
            }
            .onEach {
                when (it) {
                    is DataResult.Error -> {
                        mainEventsUseCase.logoutUser(true)
                        flowOf(it)
                    }

                    is DataResult.Success -> flowOf(it)
                }
            }
            .first()
    }
}
