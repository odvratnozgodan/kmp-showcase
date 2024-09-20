package core.common.base.navigation

import androidx.navigation.NavController
import androidx.navigation.navOptions

sealed class NavigationState : NavigationHandler {
    data object NavigateUp : NavigationState() {
        override fun navigate(navController: NavController) {
            navController.navigateUp()
        }
    }

    class NavigateToDestination(
        private val destination: ScreenNavigationRoute,
        private val clearBackStack: Boolean = false,
        private val animateTransition: Boolean = true,
        private val launchSingleTop: Boolean = false,
    ) : NavigationState() {

        override fun navigate(navController: NavController) {
            val navOptions = if (!clearBackStack) {
                navOptions {
                    launchSingleTop = this@NavigateToDestination.launchSingleTop
                }
            } else {
                // TODO: Check if this works
                val popToDestination = navController.currentBackStack.value
                    .firstOrNull { it.destination.navigatorName == "fragment" }
                    ?.destination
                navOptions {
                    launchSingleTop = true
                    restoreState = false
                    popToDestination?.route?.let {
                        popUpTo(it) {
                            saveState = false
                            inclusive = true
                        }
                    }
                }
            }
            navController.navigate(route = destination, navOptions = navOptions)
        }
    }
}
