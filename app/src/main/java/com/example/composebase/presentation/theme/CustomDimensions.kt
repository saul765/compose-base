package com.example.composebase.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class CustomDimensions(
    val dimen1: Dp = 1.dp,
    val dimen14: Dp = 14.dp,
    val dimen16: Dp = 16.dp,
    val dimen100: Dp = 100.dp

)

val LocalCustomDimensions = staticCompositionLocalOf { CustomDimensions() }

val MaterialTheme.customDimens: CustomDimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomDimensions.current