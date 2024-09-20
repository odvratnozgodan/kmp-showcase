package core.common.base.navigation

import kotlinx.serialization.Serializable

/**
 * Enum class used to represent the navigation route.
 *
 * @param name The path for the deep link
 * @arguments The arguments that can be provided(optional).
 */
// TODO: Try to simplify deeplink
@Serializable
sealed class ScreenNavigationRoute(val name: String, val requiresLoggedInUser: Boolean = false) {

    @Serializable
    data object Home : ScreenNavigationRoute("home", requiresLoggedInUser = true)

    @Serializable
    data class RecipeDetails(val id: Int) : ScreenNavigationRoute("recipe/{id}", requiresLoggedInUser = true)

    @Serializable
    data class Login(val sessionExpired: Boolean = false) : ScreenNavigationRoute("login")

    @Serializable
    data object NoAction : ScreenNavigationRoute("")
}
