package feature.authentication.di

import feature.authentication.domain.usecase.SignIn
import feature.authentication.presentation.login.LoginViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureModuleAuthentication = module {
    single { SignIn(get(), get(), get(), get()) }

    viewModelOf(::LoginViewModel)
}
