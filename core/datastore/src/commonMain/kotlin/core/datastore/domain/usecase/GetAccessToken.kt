package core.datastore.domain.usecase

import core.common.base.usecese.BaseUseCase
import core.common.base.usecese.DataResult
import core.common.base.usecese.MainEventsUseCase
import core.datastore.data.repository.TokenRepository

class GetAccessToken(private val tokenRepository: TokenRepository, private val mainEventsUseCase: MainEventsUseCase) :
    BaseUseCase<String>() {

    suspend operator fun invoke(): String? = when (val result = tokenRepository.getAccessToken()) {
        is DataResult.Error -> {
            mainEventsUseCase.logoutUser(true)
            null
        }

        is DataResult.Success -> result.data
    }
}
