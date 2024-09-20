package core.user.data.remote.datasource

import core.common.base.usecese.DataResult
import core.user.data.remote.model.User

interface UserDataSource {
    suspend fun fetchUserProfile(): DataResult<User>
}
