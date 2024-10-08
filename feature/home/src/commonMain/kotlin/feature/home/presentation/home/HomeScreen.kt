package feature.home.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import app.cash.paging.PagingData
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import app.cash.paging.compose.itemKey
import core.ui.components.CoreTopAppBar
import core.ui.theme.TemplateAppTheme
import feature.home.presentation.home.components.HomeListItem
import feature.home.presentation.home.components.HomeListItemLoading
import kmp_showcase.feature.home.generated.resources.Res
import kmp_showcase.feature.home.generated.resources.home_top_bar_title
import kmp_showcase.feature.home.generated.resources.no_items
import kotlinx.coroutines.flow.emptyFlow
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import recipes.data.remote.model.Recipe

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinInject<HomeViewModel>()) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    HomeScreenContent(
        state,
        recipesPager = viewModel.recipesPager.collectAsLazyPagingItems(),
        handleEvent = { viewModel.handleEvent(it) }
    )
}

@Composable
fun HomeScreenContent(viewState: HomeViewState, recipesPager: LazyPagingItems<Recipe>, handleEvent: (event: HomeEvent) -> Unit) {
    TemplateAppTheme(
        topBar = {
            CoreTopAppBar(
                title = stringResource(Res.string.home_top_bar_title)
            )
        }
    ) {
        val state = rememberPullToRefreshState()
        PullToRefreshBox(
            modifier = Modifier
                .padding(it),
            onRefresh = {
                recipesPager.refresh()
            },
            isRefreshing = recipesPager.loadState.refresh is LoadState.Loading
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(
                    vertical = 8.dp
                )
            ) {
                when (recipesPager.loadState.refresh) {
                    is LoadState.Error -> {}
                    LoadState.Loading -> itemsLoading()
                    is LoadState.NotLoading -> {
                        if (recipesPager.itemCount > 0) {
                            items(
                                recipesPager.itemCount,
                                key = recipesPager.itemKey { it.id }
                            ) { index ->
                                val recipe = recipesPager[index]
                                recipe?.let {
                                    HomeListItem(
                                        recipe = recipe,
                                        onClicked = {
                                            handleEvent(HomeEvent.OnItemClicked(recipe = it))
                                        }
                                    )
                                }
                            }
                        } else {
                            item {
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = stringResource(Res.string.no_items),
                                        style = MaterialTheme.typography.titleLarge,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 2
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun LazyListScope.itemsLoading() {
    items(count = 4) {
        HomeListItemLoading()
    }
}

// @ThemePreviews
@Composable
@Preview
fun RecipesListScreenPreview() {
    TemplateAppTheme {
        HomeScreenContent(
            viewState = HomeViewState(),
            handleEvent = {},
            recipesPager = emptyFlow<PagingData<Recipe>>().collectAsLazyPagingItems()
        )
    }
}
