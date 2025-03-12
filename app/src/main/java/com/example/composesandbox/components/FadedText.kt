package com.example.composesandbox.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composesandbox.extensions.horizontalFadeGradient

@Composable
fun FadedText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    truncationDirection: TruncationDirection = TruncationDirection.START,
    fadeLength: Dp,
) {
    val textMeasurer = rememberTextMeasurer()

    val fadeDirection = if ((truncationDirection == TruncationDirection.START) == (LocalLayoutDirection.current == LayoutDirection.Ltr)) {
        FadeDirection.LEFT
    } else {
        FadeDirection.RIGHT
    }

    val textAlignment = if (fadeDirection == FadeDirection.LEFT) {
        TextAlign.Right
    } else {
        TextAlign.Left
    }

    SubcomposeLayout(
        modifier = modifier,
    ) { constraints ->
        val textLayoutResult = textMeasurer.measure(
            text = text,
            maxLines = 1,
            style = style.copy(
                textAlign = textAlignment
            ),
            constraints = constraints,
        )

        val visibleCharacters = textLayoutResult.getLineEnd(0, true)
        val truncatedText = if (fadeDirection == FadeDirection.LEFT) {
            text.takeLast(visibleCharacters)
        } else {
            text.take(visibleCharacters)
        }

        val truncatedPlaceable = subcompose("truncated") {
            Text(
                text = truncatedText,
                modifier = modifier.horizontalFadeGradient(
                    fadeLength = fadeLength,
                    fadeDirection = fadeDirection,
                ),
                textAlign = textAlignment,
                style = style,
                maxLines = 1,
            )
        }.first().measure(constraints)

        layout(truncatedPlaceable.width, truncatedPlaceable.height) {
            truncatedPlaceable.place(0, 0)
        }
    }
}

enum class FadeDirection {
    LEFT,
    RIGHT,
}

enum class TruncationDirection {
    START,
    END,
}

/**
 * Preview of FadingText
 */
@Preview(showBackground = true)
@Composable
fun FadingTextPreview() {
    FadedText(
        text = "somesubdomain.google.com.roguewebsite.com",
        modifier = Modifier.width(200.dp),
        style = TextStyle(fontSize = 16.sp),
        truncationDirection = TruncationDirection.START,
        fadeLength = 50.dp,
    )
}

/**
 * Preview of FadingText with end truncation and fade
 */
@Preview(showBackground = true)
@Composable
fun EndFadingTextPreview() {
    FadedText(
        text = "somesubdomain.google.com.roguewebsite.com",
        modifier = Modifier.width(200.dp),
        style = TextStyle(fontSize = 16.sp),
        truncationDirection = TruncationDirection.END,
        fadeLength = 60.dp,
    )
}

/**
 * Preview of FadingText with end truncation and fade and RTL language
 */
@Preview(showBackground = true)
@Composable
fun EndRTLFadingTextPreview() {
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        FadedText(
            text = "somesubdomain.google.com.roguewebsite.com",
            modifier = Modifier.width(200.dp),
            style = TextStyle(fontSize = 16.sp),
            truncationDirection = TruncationDirection.END,
            fadeLength = 60.dp,
        )
    }
}

/**
 * Preview of FadingText with end truncation and fade and RTL language
 */
@Preview(showBackground = true)
@Composable
fun RTLFadingTextPreview() {
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        FadedText(
            text = "somesubdomain.google.com.roguewebsite.com",
            modifier = Modifier.width(200.dp),
            style = TextStyle(fontSize = 16.sp),
            truncationDirection = TruncationDirection.START,
            fadeLength = 60.dp,
        )
    }
}