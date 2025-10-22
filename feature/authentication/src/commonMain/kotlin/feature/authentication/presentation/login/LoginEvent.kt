package feature.authentication.presentation.login

import core.ui.viewmodel.ViewEvent
import core.user.data.remote.model.User

sealed class LoginEvent : ViewEvent {
    data class OnReceivedArguments(val sessionExpired: Boolean) : LoginEvent()
    data class OnLogin(val email: String, val password: String) : LoginEvent()
    data class LoginSuccess(val user: User) : LoginEvent()
    data class LoginFailed(val exception: Throwable) : LoginEvent()
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data object BottomSheetDismissed : LoginEvent()
}
