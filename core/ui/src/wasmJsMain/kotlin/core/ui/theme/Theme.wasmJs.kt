package core.ui.theme

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

actual val baseModifier: Modifier =
    Modifier
        .padding(horizontal = 100.dp)
//        .consumeWindowInsets(WindowInsets(left = 100.dp, right = 100.dp))
//        .windowInsetsPadding(WindowInsets(left = 100.dp, right = 100.dp))
//        .widthIn(max = 380.dp)
//        .width(380.dp)

actual val baseInsets: WindowInsets = WindowInsets(left = 100.dp, right = 100.dp)
