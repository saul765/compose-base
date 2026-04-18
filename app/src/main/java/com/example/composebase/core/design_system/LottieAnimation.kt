package com.example.composebase.core.design_system

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.composebase.core.design_system.lottie.LottieAnimationType

/**
 * Renders a Lottie animation from the provided [animationType].
 *
 * @param animationType source used to resolve the Lottie composition.
 * @param modifier modifier applied to the rendered animation.
 * @param isPlaying controls whether the animation should play.
 * @param size preferred size for the rendered animation.
 */
@Composable
fun LottieAnimation(
    animationType: LottieAnimationType,
    modifier: Modifier = Modifier,
    isPlaying: Boolean = true,
    size: DpSize = DpSize.Unspecified
) {
    val preloaderLottieComposition by rememberLottieComposition(
        when (animationType) {
            is LottieAnimationType.Raw -> LottieCompositionSpec.RawRes(animationType.resId)
            is LottieAnimationType.Url -> LottieCompositionSpec.Url(animationType.value)
        }
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        isPlaying = isPlaying,
        modifier = modifier.size(size = size)
    )
}
