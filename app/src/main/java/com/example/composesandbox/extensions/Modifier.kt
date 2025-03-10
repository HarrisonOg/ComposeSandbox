package com.example.composesandbox.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

const val DEFAULT_FADE_ALPHA = .99f
const val DEFAULT_FADE_LEFT_START = 0f
const val DEFAULT_FADE_RIGHT_START = 1F

fun Modifier.horizontalFadeGradient(
    fadeLength: Dp = 100.dp,
    fadeAlpha: Float = DEFAULT_FADE_ALPHA,
    fadeLeft: Boolean = true,
) : Modifier = this.then(
    Modifier.graphicsLayer { alpha = fadeAlpha }
        .drawWithContent {
            drawContent()
            val fraction = fadeLength.toFraction(totalWidth = this.size.width.toDp())

            val brush = if (fadeLeft) {
                Brush.horizontalGradient(
                    DEFAULT_FADE_LEFT_START to Color.Transparent,
                    (size.width * fraction) to Color.Black,
                )
            } else {
                val fadeStartPercent = DEFAULT_FADE_RIGHT_START - (size.width * fraction)

                Brush.horizontalGradient(
                    fadeStartPercent to Color.Black,
                    1f to Color.Transparent,
                )
            }

            drawRect(
                brush = brush,
                blendMode = BlendMode.DstIn
            )
        }
)

fun Dp.toFraction(totalWidth: Dp): Float {
    return (this / totalWidth).coerceIn(0f, 1f)
}