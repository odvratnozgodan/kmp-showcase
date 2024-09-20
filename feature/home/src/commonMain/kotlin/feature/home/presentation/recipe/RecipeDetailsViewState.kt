package feature.home.presentation.recipe

import core.ui.viewmodel.ViewState
import recipes.data.remote.model.RecipeDetails

data class RecipeDetailsViewState(val loading: Boolean = false, val recipeId: Int? = null, val recipe: RecipeDetails? = null) : ViewState
