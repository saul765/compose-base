package com.example.composebase.core.design_system


import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun GAFLoadingWheel(
    modifier: Modifier = Modifier,
) {


    val infiniteTransition = rememberInfiniteTransition(label = "wheel transition")

    val startValue = if (LocalInspectionMode.current) 0F else 1F
    val floatAnimValues = (0 until NUM_OF_LINES).map { remember { Animatable(startValue) } }
    LaunchedEffect(floatAnimValues) {
        (0 until NUM_OF_LINES).map { index ->
            launch {
                floatAnimValues[index].animateTo(
                    targetValue = 0F,
                    animationSpec = tween(
                        durationMillis = 100,
                        easing = FastOutSlowInEasing,
                        delayMillis = 40 * index,
                    ),
                )
            }
        }
    }

    MaterialTheme {
        val rotationAnim by infiniteTransition.animateFloat(
            initialValue = 0F,
            targetValue = 360F,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = ROTATION_TIME, easing = LinearEasing),
            ),
            label = "wheel rotation animation",
        )

        val baseLineColor = MaterialTheme.colorScheme.primary
        val progressLineColor = MaterialTheme.colorScheme.onPrimary

        val colorAnimValues = (0 until NUM_OF_LINES).map { index ->
            infiniteTransition.animateColor(
                initialValue = baseLineColor,
                targetValue = baseLineColor,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = ROTATION_TIME / 2
                        progressLineColor at ROTATION_TIME / NUM_OF_LINES / 2 using LinearEasing
                        baseLineColor at ROTATION_TIME / NUM_OF_LINES using LinearEasing
                    },
                    repeatMode = RepeatMode.Restart,
                    initialStartOffset = StartOffset(ROTATION_TIME / NUM_OF_LINES / 2 * index),
                ),
                label = "wheel color animation",
            )
        }

        Canvas(
            modifier = modifier
                .size(48.dp)
                .padding(8.dp)
                .graphicsLayer { rotationZ = rotationAnim }
                .testTag("loadingWheel"),
        ) {
            repeat(NUM_OF_LINES) { index ->
                rotate(degrees = index * 30f) {
                    drawLine(
                        color = colorAnimValues[index].value,
                        alpha = if (floatAnimValues[index].value < 1f) 1f else 0f,
                        strokeWidth = 4F,
                        cap = StrokeCap.Round,
                        start = Offset(size.width / 2, size.height / 4),
                        end = Offset(
                            size.width / 2,
                            floatAnimValues[index].value * size.height / 4
                        ),
                    )
                }
            }
        }
    }

}


private const val ROTATION_TIME = 12000
private const val NUM_OF_LINES = 12
