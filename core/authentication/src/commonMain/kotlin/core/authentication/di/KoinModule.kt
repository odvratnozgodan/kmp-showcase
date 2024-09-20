package core.authentication.di

import core.authentication.data.remote.datasource.AuthenticationDataSource
import core.authentication.data.remote.datasource.AuthenticationDataSourceImpl
import core.authentication.data.remote.service.AuthenticationService
import core.authentication.data.repository.repo.AuthenticationRepository
import core.authentication.data.repository.repo.AuthenticationRepositoryImpl
import core.authentication.domain.usecase.RefreshTokenAuthenticator
import core.common.base.usecese.DataResult
import core.datastore.domain.usecase.GetAccessToken
import core.datastore.domain.usecase.GetRefreshToken
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coreModuleAuthentication = module {
    single(named("authenticatedHttpClient")) {
        get<HttpClient>(qualifier = named("baseHttpClient"))
            .config {
                install(Auth) {
                    bearer {
                        loadTokens {
                            val getAccessToken = get<GetAccessToken>().invoke()
                            val getRefreshToken = get<GetRefreshToken>().invoke()
                            BearerTokens(getAccessToken ?: "", getRefreshToken ?: "")
                        }
                        refreshTokens {
                            // Refresh tokens and return them as the 'BearerTokens' instance
                            when (val refreshToken = get<RefreshTokenAuthenticator>().invoke()) {
                                is DataResult.Error -> {
                                    // Do nothing
                                    null
                                }
                                is DataResult.Success -> BearerTokens(
                                    accessToken = refreshToken.data.token,
                                    refreshToken = refreshToken.data.refreshToken
                                )
                            }
                        }
                    }
                }
            }
    }

    single<AuthenticationService> {
        AuthenticationService()
    }

    single<AuthenticationDataSource> {
        AuthenticationDataSourceImpl(
            get<HttpClient>(qualifier = named("defaultHttpClient")),
            get()
        )
    }

    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get()) }

    single {
        RefreshTokenAuthenticator(get(), get(), get(), get(), get(), get())
    }
}
