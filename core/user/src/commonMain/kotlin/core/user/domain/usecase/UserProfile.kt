package core.user.domain.usecase

import core.common.base.usecese.BaseUseCase
import core.common.base.usecese.DataResult
import core.user.data.remote.model.User
import core.user.data.repository.repo.UserRepository

class UserProfile(private val userRepository: UserRepository) : BaseUseCase<User>() {

    suspend fun fetch(): DataResult<User> = userRepository.fetchUserProfile()
}
