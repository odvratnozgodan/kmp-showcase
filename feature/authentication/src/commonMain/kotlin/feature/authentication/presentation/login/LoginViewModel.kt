package feature.authentication.presentation.login

import androidx.lifecycle.viewModelScope
import core.common.base.navigation.NavigationState
import core.common.base.navigation.ScreenNavigationRoute
import core.common.base.usecese.DataResult
import core.ui.viewmodel.BaseViewModel
import feature.authentication.domain.usecase.SignIn
import kotlinx.coroutines.launch

class LoginViewModel(private val signIn: SignIn) : BaseViewModel<LoginEvent, LoginViewState>() {

    override fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.SignIn -> signInUser(event.email, event.password)
            is LoginEvent.EmailChanged -> setState { copy(username = event.email, emailError = false, emailMessage = "") }
            is LoginEvent.PasswordChanged -> setState { copy(password = event.password, passwordError = false, passwordMessage = "") }
            is LoginEvent.OnReceivedArguments -> setState {
                copy(
                    sessionExpiredError = event.sessionExpired
                )
            }

            LoginEvent.BottomSheetDismissed -> setState {
                copy(
                    sessionExpiredError = false
                )
            }
        }
    }

    private fun signInUser(email: String, password: String) {
        clearError()
        setState { copy(loading = true) }
        viewModelScope.launch {
            when (val result = signIn.invoke(email, password)) {
                is DataResult.Error -> {
                    setState {
                        copy(
                            loading = false,
                            emailError = true,
                            emailMessage = result.errorBody.message ?: ""
                        )
                    }
                }

                is DataResult.Success -> {
                    setState { copy(loading = false) }
                    navigateToHome()
                }
            }
        }
    }

    private fun clearError() {
        setState {
            copy(
                emailError = false,
                emailMessage = "",
                passwordError = false,
                passwordMessage = ""
            )
        }
    }

    private fun navigateToHome() {
        viewModelScope.launch {
            navigate(
                NavigationState.NavigateToDestination(
                    destination = ScreenNavigationRoute.Home,
                    clearBackStack = true
                )
            )
        }
    }

    override fun getInitialViewState(): LoginViewState = LoginViewState()
}
