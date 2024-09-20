package core.user.di

import core.user.data.remote.datasource.UserDataSource
import core.user.data.remote.datasource.UserDataSourceImpl
import core.user.data.remote.service.UserService
import core.user.data.repository.repo.UserRepository
import core.user.data.repository.repo.UserRepositoryImpl
import core.user.domain.usecase.LogoutUser
import core.user.domain.usecase.UserProfile
import io.ktor.client.HttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coreModuleUser = module {
    single { LogoutUser(get(), get()) }
    single { UserProfile(get()) }

    single<UserDataSource> {
        UserDataSourceImpl(
            get<HttpClient>(qualifier = named("authenticatedHttpClient")),
            get()
        )
    }

    single<UserRepository> { UserRepositoryImpl(get()) }

    single<UserService> {
        UserService()
    }
}
