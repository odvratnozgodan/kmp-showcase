package feature.authentication.presentation.login

import core.ui.viewmodel.State

sealed interface LoginState : State {

    data object LoadingData : LoginState

    data object SessionExpired : LoginState

    sealed interface WithData : LoginState {

        data class PendingInput(val email: String = "michaelw", val password: String = "michaelwpass") : WithData
        data class LoggingIn(val email: String, val password: String) : WithData
        data class LoginFailure(
            val email: String,
            val password: String,
            val error: Throwable,
        ) : WithData

    }

    data object LoginSuccess : LoginState
}
