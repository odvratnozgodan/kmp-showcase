package recipes.data.remote.datasource

import core.common.base.usecese.DataResult
import recipes.data.remote.model.RecipeDetails
import recipes.data.remote.model.Recipes

interface RecipesDataSource {
    suspend fun fetchRecipes(startIndex: Int, limit: Int): DataResult<Recipes>
    suspend fun getRecipe(id: Int): DataResult<RecipeDetails>
}
