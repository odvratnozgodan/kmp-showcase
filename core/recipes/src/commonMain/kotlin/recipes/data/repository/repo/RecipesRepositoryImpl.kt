package recipes.data.repository.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import core.common.base.usecese.DataResult
import kotlinx.coroutines.flow.Flow
import recipes.data.remote.datasource.RecipesDataSource
import recipes.data.remote.model.Recipe
import recipes.data.remote.model.RecipeDetails

class RecipesRepositoryImpl(private val recipesDataSource: RecipesDataSource) : RecipesRepository {

    private val pager = Pager(
        config = PagingConfig(pageSize = 10, initialLoadSize = 10),
        pagingSourceFactory = {
            RecipesPagingSource(recipesDataSource)
        }
    )

    override val pagingData: Flow<PagingData<Recipe>>
        get() = pager.flow

    override suspend fun getRecipe(id: Int): DataResult<RecipeDetails> = recipesDataSource.getRecipe(id)
}
