package feature.home.presentation.home

import core.ui.viewmodel.ViewState
import core.user.data.remote.model.User
import recipes.data.remote.model.Recipe

data class HomeViewState(
    val loading: Boolean = false,
    val userProfile: User? = null,
    val loadingRecipes: Boolean = false,
    val recipes: List<Recipe> = listOf(),
) : ViewState
