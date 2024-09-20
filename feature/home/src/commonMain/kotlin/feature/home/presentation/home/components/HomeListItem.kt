package feature.home.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import core.ui.components.CoreCard
import core.ui.modifier.shimmer
import core.ui.theme.TemplateAndroidAppThemeWithoutScaffold
import org.jetbrains.compose.ui.tooling.preview.Preview
import recipes.data.remote.model.Recipe
import recipes.data.remote.model.TestRecipe

@Composable
fun HomeListItem(recipe: Recipe, onClicked: (recipe: Recipe) -> Unit = {}) {
    CoreCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClicked.invoke(recipe)
            }
    ) {
        ListItemContent(recipe)
    }
}

@Composable
private fun ListItemContent(recipe: Recipe) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val modifier = Modifier.fillMaxWidth()
            .height(120.dp)
            .clip(shape = CardDefaults.shape)
        var imageIsLoading = remember { false }
        Box(modifier = modifier.shimmer(imageIsLoading)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(recipe.image)
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
        Text(
            text = recipe.name,
            style = MaterialTheme.typography.titleLarge,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
    }
}

@Composable
fun HomeListItemLoading() {
    CoreCard(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val modifier = Modifier.fillMaxWidth()
                .height(120.dp)
                .clip(shape = CardDefaults.shape)
            Box(modifier = modifier.shimmer(true))
            Text(
                text = "",
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
    }
}

// @ThemePreviews
@Preview
@Composable
fun ListItemPreview() {
    TemplateAndroidAppThemeWithoutScaffold {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HomeListItem(
                recipe = TestRecipe
            )
            HomeListItem(
                recipe = TestRecipe
            )
            HomeListItem(
                recipe = TestRecipe
            )
            HomeListItem(
                recipe = TestRecipe
            )
            HomeListItemLoading()
        }
    }
}
