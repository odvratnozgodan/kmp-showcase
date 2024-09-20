package feature.authentication.domain.usecase

import core.authentication.data.repository.repo.AuthenticationRepository
import core.common.base.usecese.BaseUseCase
import core.common.base.usecese.DataResult
import core.common.extensions.asFlow
import core.common.extensions.doOnResultSuccess
import core.common.extensions.transformOnResultSuccess
import core.datastore.domain.usecase.SetAccessToken
import core.datastore.domain.usecase.SetRefreshToken
import core.user.data.remote.model.User
import core.user.domain.usecase.UserProfile
import kotlinx.coroutines.flow.first

class SignIn(
    private val authenticationRepository: AuthenticationRepository,
    private val setAccessToken: SetAccessToken,
    private val setRefreshToken: SetRefreshToken,
    private val userProfile: UserProfile,
) : BaseUseCase<User>() {

    suspend operator fun invoke(username: String, password: String): DataResult<User> {
        val signInResult = authenticationRepository.login(username, password).asFlow()
        return signInResult
            .doOnResultSuccess {
                setAccessToken(it.data.token)
            }
            .doOnResultSuccess {
                setRefreshToken(it.data.refreshToken)
            }
            .transformOnResultSuccess {
                userProfile.fetch()
            }
            .first()
    }
}
