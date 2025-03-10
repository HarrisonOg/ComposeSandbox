package com.example.composesandbox

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FadingTruncatedText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    truncationDirection: TruncationDirection = TruncationDirection.START,
    fadeLength: Dp,
) {
    val textMeasurer = rememberTextMeasurer()

    SubcomposeLayout { constraints ->
        val measuredWidth = constraints.maxWidth

        val textLayoutResult = textMeasurer.measure(
            text = text,
            style = style,
            constraints = Constraints(maxWidth = measuredWidth),
        )

        val visibleCharacters = textLayoutResult.getLineEnd(0, true)
        val truncatedText = if (truncationDirection == TruncationDirection.START) {
            text.takeLast(visibleCharacters)
        } else {
            text.take(visibleCharacters)
        }

        val truncatedPlaceable = subcompose("truncated") {
            Text(
                text = truncatedText,
                modifier = modifier.horizontalFadeGradient(
                    fadeLength = fadeLength,
                    fadeLeft = (truncationDirection == TruncationDirection.START),
                ),
                style = style,
                maxLines = 1,
            )
        }.first().measure(constraints)

        layout(truncatedPlaceable.width, truncatedPlaceable.height) {
            truncatedPlaceable.place(0, 0)
        }
    }
}

enum class TruncationDirection {
    START,
    END,
}

/**
 * Preview of FadingFrontTruncatedText
 */
@Preview(showBackground = true)
@Composable
fun FadingTruncatedTextPreview() {
    FadingTruncatedText(
        text = "This is a very long string that will be truncated from the beginning",
        modifier = Modifier.width(200.dp),
        style = TextStyle(fontSize = 16.sp),
        truncationDirection = TruncationDirection.START,
        fadeLength = 60.dp,
    )
}

/**
 * Preview of FadingFrontTruncatedText with end truncation and fade
 */
@Preview(showBackground = true)
@Composable
fun EndFadingTruncatedTextPreview() {
    FadingTruncatedText(
        text = "This will be end truncated and is a very long string that will be truncated from the beginning",
        modifier = Modifier.width(200.dp),
        style = TextStyle(fontSize = 16.sp),
        truncationDirection = TruncationDirection.END,
        fadeLength = 60.dp,
    )
}