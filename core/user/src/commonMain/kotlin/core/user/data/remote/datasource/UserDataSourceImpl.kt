package core.user.data.remote.datasource

import core.common.base.usecese.DataResult
import core.network.safeApiCall
import core.user.data.remote.model.User
import core.user.data.remote.service.UserService
import io.ktor.client.HttpClient

class UserDataSourceImpl(private val httpClient: HttpClient, private val userService: UserService) : UserDataSource {

    override suspend fun fetchUserProfile(): DataResult<User> = httpClient.safeApiCall<User>(userService.getProfile())
}
