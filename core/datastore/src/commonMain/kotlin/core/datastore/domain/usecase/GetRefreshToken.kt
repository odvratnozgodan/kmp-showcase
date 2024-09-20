package core.datastore.domain.usecase

import core.common.base.usecese.BaseUseCase
import core.common.base.usecese.DataResult
import core.datastore.data.repository.TokenRepository

class GetRefreshToken(private val tokenRepository: TokenRepository) : BaseUseCase<String>() {

    suspend operator fun invoke(): String? = when (val result = tokenRepository.getRefreshToken()) {
        is DataResult.Error -> {
            null
        }

        is DataResult.Success -> result.data
    }
}
