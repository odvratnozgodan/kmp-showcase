package feature.authentication.presentation.login

import core.ui.viewmodel.ViewState

sealed interface LoginViewState : ViewState {

    data object Loading : LoginViewState

    data class LoginInput(
        val loading: Boolean = false,
        val username: String = "michaelw",
        val password: String = "michaelwpass",
        val emailErrorMessage: String? = null,
        val passwordErrorMessage: String? = null,
        val sessionExpiredError: Boolean = false,
    ) : LoginViewState

    data object Success : LoginViewState
}
