package core.user.domain.usecase

import core.common.base.usecese.BaseUseCase
import core.common.base.usecese.DataResult
import core.common.extensions.asFlow
import core.common.extensions.transformOnResultSuccess
import core.datastore.domain.usecase.SetAccessToken
import core.datastore.domain.usecase.SetRefreshToken
import kotlinx.coroutines.flow.first

class LogoutUser(private val setRefreshToken: SetRefreshToken, private val setAccessToken: SetAccessToken) : BaseUseCase<String>() {

    suspend operator fun invoke(): DataResult<Boolean> = setRefreshToken.invoke("").asFlow()
        .transformOnResultSuccess {
            setAccessToken("")
        }
        .first()
}
