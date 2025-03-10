# ComposeSandbox
This will hold my jetpack composable experiments, modifiers, extensions, and components that I work on.


3/10/2025
- components/FadingTextView.kt : 1st attempt at making a [Text] component that added a horizontal fade to the start of a text and front-truncated the text to what would fit on screen.
- components/FadingTruncatedtext.kt : Second attempt at making Fading Truncated Text but this time it has the ability to truncate from front or end and add fade to the same direction.
- extensions/Modifier.kt : added Modifier.horizontalFadeGradient and Dp.toFraction to break apart the fading logic and the screen fraction logic.