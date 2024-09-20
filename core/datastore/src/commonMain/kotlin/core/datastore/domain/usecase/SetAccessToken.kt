package core.datastore.domain.usecase

import core.common.base.usecese.BaseUseCase
import core.common.base.usecese.DataResult
import core.datastore.data.repository.TokenRepository

class SetAccessToken(private val tokenRepository: TokenRepository) : BaseUseCase<Boolean>() {

    suspend operator fun invoke(token: String): DataResult<Boolean> = tokenRepository.setAccessToken(token)
}
