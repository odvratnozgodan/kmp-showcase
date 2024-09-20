package feature.home.presentation.recipe

import androidx.lifecycle.viewModelScope
import core.common.base.usecese.DataResult
import core.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import recipes.domain.usecase.RecipesUseCase

class RecipeDetailsViewModel(private val recipes: RecipesUseCase) : BaseViewModel<RecipeDetailsEvent, RecipeDetailsViewState>() {

    override fun refreshData() {
        viewModelScope.launch {
            viewState.value.recipeId?.let {
                getRecipe(it)
            }
        }
    }

    override fun handleEvent(event: RecipeDetailsEvent) {
        when (event) {
            RecipeDetailsEvent.GoBack -> navigateBack()
            is RecipeDetailsEvent.OnReceivedArguments -> {
                println(">>> OnReceivedArguments")
                event.recipeId?.let {
                    getRecipe(it)
                } ?: navigateBack()
            }
        }
    }

    private fun getRecipe(recipeId: Int) {
        viewModelScope.launch {
            setState { copy(loading = true) }
            when (val recipes = recipes.get(recipeId)) {
                is DataResult.Error -> {
                    // TODO: handle error
                }

                is DataResult.Success -> setState {
                    copy(
                        loading = false,
                        recipeId = recipeId,
                        recipe = recipes.data
                    )
                }
            }
        }
    }

    override fun getInitialViewState(): RecipeDetailsViewState = RecipeDetailsViewState(loading = true)
}
