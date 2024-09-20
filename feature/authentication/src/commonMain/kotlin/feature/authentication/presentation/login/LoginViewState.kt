package feature.authentication.presentation.login

import core.ui.viewmodel.ViewState

data class LoginViewState(
    val loading: Boolean = false,
    val username: String = "michaelw",
    val password: String = "michaelwpass",
    val emailError: Boolean = false,
    val emailMessage: String = "",
    val passwordError: Boolean = false,
    val passwordMessage: String = "",
    val sessionExpiredError: Boolean = false,
) : ViewState
