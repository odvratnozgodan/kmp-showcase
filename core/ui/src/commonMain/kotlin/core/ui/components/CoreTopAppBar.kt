package core.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.ui.theme.TemplateAndroidAppThemeWithoutScaffold
import core.ui.theme.ThemePreviews
import kmp_showcase.core.common.generated.resources.Res
import kmp_showcase.core.common.generated.resources.save_label
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoreTopAppBar(
    title: String,
    backClicked: (() -> Unit)? = null,
    onSaveButtonClick: (() -> Unit)? = null,
    saveButtonEnabled: Boolean = true,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    showBorder: Boolean = false,
    actions: @Composable (() -> Unit) = {
        onSaveButtonClick?.let {
            TextButton(onClick = it, enabled = saveButtonEnabled) {
                Text(
                    text = stringResource(Res.string.save_label),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    },
) {
    CoreTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .then(
                if (showBorder) {
                    Modifier
                        .border(width = 1.dp, color = Color(0x1A6D2AE8))
                } else {
                    Modifier
                }
            ),
        title = {
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
        },
        navigationIcon = {
            backClicked?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        actions = {
            onSaveButtonClick?.let {
                TextButton(onClick = it, enabled = saveButtonEnabled) {
                    Text(
                        text = stringResource(Res.string.save_label),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        },
        colors = colors
    )
}

@Composable
fun CoreTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        windowInsets = windowInsets,
        colors = colors,
        scrollBehavior = scrollBehavior
    )
}

@ThemePreviews
@Composable
fun CoreTopBarPreview() {
    TemplateAndroidAppThemeWithoutScaffold {
        CoreTopAppBar(
            title = "Title",
            backClicked = {},
            saveButtonEnabled = false,
            onSaveButtonClick = {}
        )
    }
}
