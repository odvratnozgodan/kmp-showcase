package feature.authentication.presentation.login

import androidx.lifecycle.viewModelScope
import core.common.base.navigation.NavigationState
import core.common.base.navigation.ScreenNavigationRoute
import core.common.extensions.fold
import core.ui.viewmodel.BaseStateViewModel
import core.ui.viewmodel.state.StateMachine
import core.ui.viewmodel.state.stateMachine
import feature.authentication.domain.usecase.SignIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(private val signIn: SignIn) : BaseStateViewModel<LoginEvent, LoginState, LoginViewState>() {

    override val stateMachine = stateMachine<LoginState, LoginEvent>(
        scope = viewModelScope,
        initialState = LoginState.LoadingData,
    ) {
        sideEffect { state ->
            println(">>> Current state: $state")
        }
        state<LoginState.LoadingData> {
            onEvent<LoginEvent.OnReceivedArguments> { _, event ->
                if (event.sessionExpired) LoginState.SessionExpired
                else LoginState.WithData.PendingInput()
            }
        }
        state<LoginState.SessionExpired> {
            onEvent<LoginEvent.BottomSheetDismissed> { _, _ ->
                LoginState.WithData.PendingInput()
            }
        }
        nestedState<LoginState.WithData> {
            state<LoginState.WithData.PendingInput> {
                onEvent<LoginEvent.EmailChanged> { state, event ->
                    state.copy(email = event.email)
                }
                onEvent<LoginEvent.PasswordChanged> { state, event ->
                    state.copy(password = event.password)
                }
                onEvent<LoginEvent.OnLogin> { event, _ ->
                    LoginState.WithData.LoggingIn(event.email, event.password)
                }
            }
            state<LoginState.WithData.LoggingIn> {
                sideEffect { state ->
                    signIn.invoke(state.email, state.password)
                        .fold(
                            onSuccess = LoginEvent::LoginSuccess,
                            onFailure = LoginEvent::LoginFailed,
                        )
                        .also(::onEvent)
                }
                onEvent<LoginEvent.LoginSuccess> { _, _ ->
                    LoginState.LoginSuccess
                }
                onEvent<LoginEvent.LoginFailed> { state, event ->
                    LoginState.WithData.LoginFailure(state.email, state.password, event.exception)
                }
            }
            state<LoginState.WithData.LoginFailure> {
                onEvent<LoginEvent.EmailChanged> { state, event ->
                    LoginState.WithData.PendingInput(event.email, state.password)
                }
                onEvent<LoginEvent.PasswordChanged> { state, event ->
                    LoginState.WithData.PendingInput(state.email, event.password)
                }
                onEvent<LoginEvent.OnLogin> { event, _ ->
                    LoginState.WithData.LoggingIn(event.email, event.password)
                }
            }
        }
        state<LoginState.LoginSuccess> {
            sideEffect {
                navigateToHome()
            }
        }
    }

    override fun mapState(state: LoginState) = when (state) {
        LoginState.LoadingData -> LoginViewState.Loading
        LoginState.SessionExpired -> LoginViewState.LoginInput(sessionExpiredError = true)
        LoginState.LoginSuccess -> LoginViewState.Success
        is LoginState.WithData.LoggingIn -> LoginViewState.LoginInput(
            loading = true,
            username = state.email,
            password = state.password,
        )

        is LoginState.WithData.LoginFailure -> LoginViewState.LoginInput(
            username = state.email,
            password = state.password,
            emailErrorMessage = state.error.message,
        )

        is LoginState.WithData.PendingInput -> LoginViewState.LoginInput(
            sessionExpiredError = false,
            username = state.email,
            password = state.password
        )
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

    override fun getInitialViewState(): LoginViewState = LoginViewState.Loading

}
