package com.example.composesandbox.extensions

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composesandbox.components.FadeDirection

const val DEFAULT_FADE_ALPHA = .99f
const val DEFAULT_FADE_LEFT_START = 0f
const val DEFAULT_FADE_RIGHT_START = 1F

fun Modifier.horizontalFadeGradient(
    fadeLength: Dp,
    fadeAlpha: Float = DEFAULT_FADE_ALPHA,
    fadeDirection: FadeDirection,
): Modifier = this.then(
    Modifier.graphicsLayer { alpha = fadeAlpha }
        .drawWithContent {
            drawContent()
            val fraction = fadeLength.toFraction(totalWidth = this.size.width.toDp())

            val brush = if (fadeDirection == FadeDirection.LEFT) {
                Brush.horizontalGradient(
                    DEFAULT_FADE_LEFT_START to Color.Transparent,
                    fraction to Color.Black,
                )
            } else {
                val fadeStartPercent = DEFAULT_FADE_RIGHT_START - fraction

                Brush.horizontalGradient(
                    fadeStartPercent to Color.Black,
                    DEFAULT_FADE_RIGHT_START to Color.Transparent,
                )
            }

            drawRect(
                brush = brush,
                blendMode = BlendMode.DstIn,
            )
        },
)

fun Dp.toFraction(totalWidth: Dp): Float {
    return (this / totalWidth).coerceIn(0f, 1f)
}