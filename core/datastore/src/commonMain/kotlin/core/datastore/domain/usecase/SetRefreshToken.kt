package core.datastore.domain.usecase

import core.common.base.usecese.BaseUseCase
import core.common.base.usecese.DataResult
import core.datastore.data.repository.TokenRepository

class SetRefreshToken(private val tokenRepository: TokenRepository) : BaseUseCase<Boolean>() {

    suspend operator fun invoke(refreshToken: String): DataResult<Boolean> = tokenRepository.setRefreshToken(refreshToken)
}
