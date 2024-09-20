package core.user.data.repository.repo

import core.common.base.usecese.DataResult
import core.user.data.remote.model.User

interface UserRepository {

    suspend fun fetchUserProfile(): DataResult<User>
}
