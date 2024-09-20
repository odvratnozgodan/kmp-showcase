package recipes.data.remote.datasource

import core.common.base.usecese.DataResult
import core.datastore.store.DataStoreManager
import core.network.safeApiCall
import io.ktor.client.HttpClient
import recipes.data.remote.model.RecipeDetails
import recipes.data.remote.model.Recipes
import recipes.data.remote.service.RecipesService

class RecipesDataSourceImpl(
    private val httpClient: HttpClient,
    private val recipesService: RecipesService,
    private val dataStoreManager: DataStoreManager,
) : RecipesDataSource {

    companion object {
        private const val RECIPES = "RECIPES"
    }

    override suspend fun fetchRecipes(startIndex: Int, limit: Int): DataResult<Recipes> =
        httpClient.safeApiCall<Recipes>(recipesService.getRecipes(startIndex, maxResults = limit))

    override suspend fun getRecipe(id: Int): DataResult<RecipeDetails> = httpClient.safeApiCall<RecipeDetails>(recipesService.getRecipe(id))
}
