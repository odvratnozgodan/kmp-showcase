package feature.authentication.presentation.login

import core.ui.viewmodel.ViewEvent

sealed class LoginEvent : ViewEvent {
    data class OnReceivedArguments(val sessionExpired: Boolean) : LoginEvent()
    data class SignIn(val email: String, val password: String) : LoginEvent()
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data object BottomSheetDismissed : LoginEvent()
}
