package feature.home.presentation.recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Badge
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import core.common.base.navigation.ScreenNavigationRoute
import core.common.extensions.ifNullOrBlank
import core.ui.components.CoreTopAppBar
import core.ui.modifier.shimmer
import core.ui.theme.TemplateAppTheme
import core.ui.theme.ThemePreviews
import org.koin.compose.koinInject

@Composable
fun RecipeDetailsScreen(
    recipe: ScreenNavigationRoute.RecipeDetails,
    viewModel: RecipeDetailsViewModel = koinInject<RecipeDetailsViewModel>(),
) {
    val state by viewModel.viewState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.handleEvent(RecipeDetailsEvent.OnReceivedArguments(recipe.id))
    }
    RecipeDetailsScreenContent(
        state,
        handleEvent = { viewModel.handleEvent(it) }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeDetailsScreenContent(viewState: RecipeDetailsViewState, handleEvent: (event: RecipeDetailsEvent) -> Unit) {
    TemplateAppTheme(
        topBar = {
            CoreTopAppBar(
                title = when (viewState.loading) {
                    true -> "Loading..."
                    false -> viewState.recipe?.name.ifNullOrBlank { "Recipe" }
                },
                backClicked = {
                    handleEvent(RecipeDetailsEvent.GoBack)
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            item {
                val modifier = Modifier.fillMaxWidth()
                    .height(240.dp)
                    .padding(horizontal = 4.dp)
                    .clip(shape = CardDefaults.shape)
                var imageIsLoading = remember { false }
                Box(modifier = modifier.shimmer(imageIsLoading)) {
                    viewState.recipe?.let {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalPlatformContext.current)
                                .data(it.image)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            onLoading = {
                                imageIsLoading = true
                            },
                            onSuccess = {
                                imageIsLoading = false
                            },
                            onError = {
                                imageIsLoading = false
                            },
                            modifier = modifier,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            viewState.recipe?.let { recipe ->
                item {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        recipe.tags.forEach {
                            Badge { Text(it) }
                        }
                    }
                }
                listItem("Prep time(min)", recipe.prepTimeMinutes.toString())
                listItem("Cook time(min)", recipe.cookTimeMinutes.toString())
                listItem("Servings", recipe.servings.toString())
                listItem("Difficulty", recipe.difficulty)
                listItem("Cuisine", recipe.cuisine)
                listItem("Rating", recipe.rating.toString())
                listItem("Review count", recipe.reviewCount.toString())
                listItem(
                    heading = "Meal type",
                    value = recipe.mealType.joinToString(separator = "\n") {
                        " • $it"
                    }
                )

                listItem(
                    heading = "Ingredients",
                    value = recipe.ingredients.joinToString(separator = ", ")
                )
                listItem(
                    heading = "Instructions",
                    value = recipe.instructions.joinToString(separator = "\n") {
                        " • $it"
                    }
                )
            }
        }
    }
}

private fun LazyListScope.listItem(heading: String, value: String) {
    item {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                modifier = Modifier,
                text = heading,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier,
                text = value,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@ThemePreviews
@Composable
fun RecipeDetailsScreenPreview() {
    TemplateAppTheme {
        RecipeDetailsScreenContent(
            viewState = RecipeDetailsViewState(),
            handleEvent = {}
        )
    }
}
