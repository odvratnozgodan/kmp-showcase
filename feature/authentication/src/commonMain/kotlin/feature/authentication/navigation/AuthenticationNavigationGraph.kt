package feature.authentication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import core.common.base.navigation.ScreenNavigationRoute
import feature.authentication.presentation.login.LoginScreen

fun NavGraphBuilder.addAuthenticationGraph() {
    composable<ScreenNavigationRoute.Login> {
        val login: ScreenNavigationRoute.Login = it.toRoute()
        LoginScreen(login)
    }
}
