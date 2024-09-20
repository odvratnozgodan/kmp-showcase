package recipes.data.repository.repo

import app.cash.paging.PagingData
import core.common.base.usecese.DataResult
import kotlinx.coroutines.flow.Flow
import recipes.data.remote.model.Recipe
import recipes.data.remote.model.RecipeDetails

interface RecipesRepository {
    val pagingData: Flow<PagingData<Recipe>>
    suspend fun getRecipe(id: Int): DataResult<RecipeDetails>
}
