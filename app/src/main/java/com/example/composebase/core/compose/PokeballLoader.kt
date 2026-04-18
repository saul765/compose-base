package com.example.composebase.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.composebase.R
import com.example.composebase.core.design_system.LottieAnimation
import com.example.composebase.core.design_system.lottie.LottieAnimationType

@Composable
fun PokeballLoader(modifier: Modifier) {
    LottieAnimation(
        animationType = LottieAnimationType.Raw(R.raw.pokeball_anim),
        size = DpSize(
            width = 200.dp,
            height = 200.dp
        ),
        modifier = modifier
    )
}
