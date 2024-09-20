package feature.home.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import core.common.base.navigation.ScreenNavigationRoute
import feature.home.presentation.home.HomeScreen
import feature.home.presentation.recipe.RecipeDetailsScreen

val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>) -> EnterTransition = {
    slideIn(
        animationSpec = tween(150, easing = FastOutSlowInEasing),
        initialOffset = {
            IntOffset(it.width / 4, 0)
        }
    )
}
val exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>) -> ExitTransition = {
    slideOut(
        animationSpec = tween(150, easing = LinearOutSlowInEasing),
        targetOffset = {
            IntOffset(it.width / 4, 0)
        }
    )
    fadeOut(animationSpec = tween(durationMillis = 100, easing = LinearOutSlowInEasing))
}

fun NavGraphBuilder.addHomeGraph() {
    composable<ScreenNavigationRoute.Home>(
        enterTransition = enterTransition
    ) {
        HomeScreen()
    }
    composable<ScreenNavigationRoute.RecipeDetails>(
        enterTransition = enterTransition,
        exitTransition = exitTransition
    ) {
        val recipe: ScreenNavigationRoute.RecipeDetails = it.toRoute()
        RecipeDetailsScreen(
            recipe = recipe
        )
    }
}
