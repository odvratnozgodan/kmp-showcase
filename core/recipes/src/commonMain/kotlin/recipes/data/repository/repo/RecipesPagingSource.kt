package recipes.data.repository.repo

import app.cash.paging.PagingSource
import app.cash.paging.PagingState
import core.common.base.usecese.DataResult
import recipes.data.remote.datasource.RecipesDataSource
import recipes.data.remote.model.Recipe

class RecipesPagingSource(private val recipesDataSource: RecipesDataSource) : PagingSource<Int, Recipe>() {
    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val startIndex = params.key ?: 0
        val limit = params.loadSize
        val response = recipesDataSource.fetchRecipes(startIndex, limit)

        return when (response) {
            is DataResult.Error -> {
                LoadResult.Error(response.errorBody)
            }

            is DataResult.Success -> {
                val prevKey = if (startIndex == 0) null else startIndex - 10
                val nextKey = (startIndex + limit).takeIf { response.data.total >= (startIndex + limit) }
                LoadResult.Page(
                    data = response.data.recipes,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
        }
    }
}
