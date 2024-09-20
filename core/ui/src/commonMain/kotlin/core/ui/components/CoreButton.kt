package core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import core.ui.theme.TemplateAppTheme
import core.ui.theme.ThemePreviews

@Composable
fun CoreButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    icon: Painter? = null,
    iconSpacing: Dp = 8.dp,
    onClick: () -> Unit,
    shape: Shape = MaterialTheme.shapes.large,
    enabled: Boolean = true,
    loading: Boolean = false,
    mainButtonColor: ButtonColors = ButtonColors(),
    contentPadding: PaddingValues = PaddingValues(start = 16.dp, end = 16.dp),
) {
    val merged = Modifier
        .wrapContentWidth()
        .then(modifier)
        .then(
            Modifier
                .height(50.dp)
        )
    Button(
        onClick = {
            if (!loading) {
                onClick.invoke()
            }
        },
        modifier = merged,
        enabled = enabled,
        contentPadding = contentPadding,
        shape = shape,
        colors = mainButtonColor
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(iconSpacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Icon(painter = it, contentDescription = null)
            }
            if (loading) {
                val color = LocalTextStyle.current.color.takeOrElse {
                    LocalContentColor.current
                }
                CircularProgressIndicator(
                    modifier = Modifier.size(14.dp),
                    color = color,
                    strokeWidth = 1.5.dp
                )
            }
            Text(
                text = text
            )
        }
    }
}

@Composable
fun ButtonColors() = ButtonDefaults.buttonColors()

@ThemePreviews
@Composable
fun CoreButtonPreview() {
    TemplateAppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CoreButton(
                text = "Button",
                onClick = {}
            )
            CoreButton(
                modifier = Modifier.wrapContentWidth(),
                text = "Button",
                onClick = {}
            )
            CoreButton(
                text = "Button",
                onClick = {},
                loading = true
            )
            CoreButton(
                text = "Button",
                onClick = {},
                enabled = false
            )
            CoreButton(
                text = "Button",
                onClick = {},
                enabled = false,
                loading = true
            )
        }
    }
}
