package core.ui.modifier

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.ui.components.CoreCard
import core.ui.theme.TemplateAppTheme
import core.ui.theme.ThemePreviews

fun Modifier.shimmer(
    loading: Boolean = true,
    widthOfShadowBrush: Int = 1500,
    angleOfAxisY: Float = 0f,
    durationMillis: Int = 1000,
): Modifier {
    if (!loading) {
        return this
    } else {
        return composed {
            this.background(
                brush = ShimmerBrush(
                    widthOfShadowBrush = widthOfShadowBrush,
                    angleOfAxisY = angleOfAxisY,
                    durationMillis = durationMillis
                )
            )
        }
    }
}

@Composable
fun ShimmerBrush(
    widthOfShadowBrush: Int = 400,
    angleOfAxisY: Float = 0f,
    durationMillis: Int = 1500,
    colors: List<Color> = shimmerColors(),
): Brush {
    val transition = rememberInfiniteTransition(label = "")

    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "Shimmer loading animation"
    )

    return Brush.linearGradient(
        colors = colors,
        start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
        end = Offset(x = translateAnimation.value, y = angleOfAxisY)
    )
}

@Composable
fun shimmerColors(): List<Color> = listOf(
    MaterialTheme.colorScheme.surfaceVariant,
    MaterialTheme.colorScheme.surface.copy(alpha = 0.4f),
    MaterialTheme.colorScheme.surface.copy(alpha = 0.4f),
    MaterialTheme.colorScheme.surfaceVariant
)

@ThemePreviews
@Composable
fun ShimmerLoadingPreview() {
    TemplateAppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Column {
                CoreCard(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(24.dp))
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .shimmer(loading = true)
                    )
                }
            }
        }
    }
}
