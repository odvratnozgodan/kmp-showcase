package recipes.domain.usecase

import app.cash.paging.PagingData
import core.common.base.usecese.BaseUseCase
import core.common.base.usecese.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import recipes.data.remote.model.Recipe
import recipes.data.remote.model.RecipeDetails
import recipes.data.remote.model.Recipes
import recipes.data.repository.repo.RecipesRepository

class RecipesUseCase(private val recipesRepository: RecipesRepository) : BaseUseCase<Recipes>() {

    private val _recipesFlow = MutableSharedFlow<DataResult<Recipes>>()

    suspend fun observeRecipes(): Flow<DataResult<Recipes>> = invoke { _recipesFlow.asSharedFlow() }

    fun getRecipes(): Flow<PagingData<Recipe>> = recipesRepository.pagingData

    suspend fun get(id: Int): DataResult<RecipeDetails> = recipesRepository.getRecipe(id)
}
