package feature.home.presentation.recipe

import core.ui.viewmodel.ViewEvent

sealed class RecipeDetailsEvent : ViewEvent {
    data object GoBack : RecipeDetailsEvent()
    data class OnReceivedArguments(val recipeId: Int?) : RecipeDetailsEvent()
}
