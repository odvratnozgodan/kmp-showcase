package feature.home.presentation.home

import androidx.lifecycle.viewModelScope
import core.common.base.navigation.NavigationState
import core.common.base.navigation.ScreenNavigationRoute
import core.ui.viewmodel.BaseViewModel
import core.user.domain.usecase.UserProfile
import kotlinx.coroutines.launch
import recipes.domain.usecase.RecipesUseCase

class HomeViewModel(private val userProfile: UserProfile, private val recipesUseCase: RecipesUseCase) :
    BaseViewModel<HomeEvent, HomeViewState>() {

    val recipesPager = recipesUseCase.getRecipes()

    override fun loadData() {
        viewModelScope.launch {
            getUserProfile()
        }
    }

    override fun handleEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnItemClicked -> {
                navigate(
                    NavigationState.NavigateToDestination(
                        destination = ScreenNavigationRoute.RecipeDetails(
                            event.recipe.id
                        )
                    )
                )
            }
        }
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            userProfile.fetch()
        }
    }

    override fun getInitialViewState(): HomeViewState = HomeViewState(loading = true)
}
