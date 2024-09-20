package core.common.base.model

import core.common.base.navigation.NavigationState

sealed class MainEvents {
    data class LogoutUser(val sessionExpired: Boolean = false) : MainEvents()
    data class Navigate(val destination: NavigationState) : MainEvents()
}
