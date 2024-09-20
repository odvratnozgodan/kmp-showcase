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
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import recipes.data.remote.model.Recipe

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinInject<HomeViewModel>()) {
    val state by viewModel.viewState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.refreshData()
            }
        }
    }
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
        LaunchedEffect(recipesPager.loadState.refresh) {
            // fetch something
            if (recipesPager.loadState.refresh is LoadState.NotLoading) {
                state.endRefresh()
            }
        }
        if (state.isRefreshing) {
            LaunchedEffect(true) {
                recipesPager.refresh()
            }
        }
        Box(
            Modifier
                .padding(it)
                .nestedScroll(state.nestedScrollConnection)
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
            PullToRefreshContainer(modifier = Modifier.align(Alignment.TopCenter), state = state)
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
