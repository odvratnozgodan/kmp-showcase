package core.user.data.repository.repo

import core.common.base.usecese.DataResult
import core.user.data.remote.datasource.UserDataSource
import core.user.data.remote.model.User

class UserRepositoryImpl(private val userDataSource: UserDataSource) : UserRepository {

    override suspend fun fetchUserProfile(): DataResult<User> = userDataSource.fetchUserProfile()
}
