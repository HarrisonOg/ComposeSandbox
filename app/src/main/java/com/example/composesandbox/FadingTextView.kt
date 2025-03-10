package com.example.composesandbox

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val FADE_ALPHA = 0.99f

/**
 * A [Text] Composable that will fade in the front and truncates the text to end visible characters.
 *
 * @param text The [String] for the Text
 * @param modifier the [Modifier] for the Text
 * @param style the [TextStyle] for the Text
 * @param fadeLength the [Float] that defines how much of the text view to fade away at the beginning
 */
@Composable
fun FadingFrontTruncatedText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    fadeLength: Float = 0.3f,
) {
    val textMeasurer = rememberTextMeasurer()

    SubcomposeLayout(
        modifier = modifier
            .graphicsLayer { alpha = FADE_ALPHA }
            .drawWithContent {
                drawContent()
                drawRect(
                    brush = Brush.horizontalGradient(
                        0f to Color.Transparent,
                        fadeLength to Color.Black,
                    ),
                    blendMode = BlendMode.DstIn,
                )
            },
    ) { constraints ->
        val measuredWidth = constraints.maxWidth

        val textLayoutResult = textMeasurer.measure(
            text = text,
            style = style,
            constraints = Constraints(maxWidth = measuredWidth),
        )

        val visibleCharacters = textLayoutResult.getLineEnd(0, true)
        val truncatedText = text.takeLast(visibleCharacters).trim()

        val truncatedPlaceable = subcompose("truncated") {
            Text(
                text = truncatedText,
                style = style,
                maxLines = 1,
            )
        }.first().measure(constraints)

        layout(truncatedPlaceable.width, truncatedPlaceable.height) {
            truncatedPlaceable.place(0, 0)
        }
    }
}

/**
 * Preview of FadingFrontTruncatedText
 */
@Preview(showBackground = true)
@Composable
fun FadingFrontTruncatedTextPreview() {
    FadingFrontTruncatedText(
        text = "This is a very long string that will be truncated from the beginning",
        modifier = Modifier.width(200.dp),
        style = TextStyle(fontSize = 16.sp),
    )
}
