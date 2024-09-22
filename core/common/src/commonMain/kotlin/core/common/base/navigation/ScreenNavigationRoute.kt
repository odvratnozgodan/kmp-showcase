package core.common.base.navigation

import kotlinx.serialization.Serializable

/**
 * Enum class used to represent the navigation route.
 */
@Serializable
sealed class ScreenNavigationRoute {

    @Serializable
    data object Home : ScreenNavigationRoute()

    @Serializable
    data class RecipeDetails(val id: Int) : ScreenNavigationRoute()

    @Serializable
    data class Login(val sessionExpired: Boolean = false) : ScreenNavigationRoute()

    @Serializable
    data object NoAction : ScreenNavigationRoute()
}
