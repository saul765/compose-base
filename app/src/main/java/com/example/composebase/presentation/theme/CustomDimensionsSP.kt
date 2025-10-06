package com.example.composebase.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class CustomDimensionsSP(
    val dimen24: TextUnit = 24.sp,
    val dimen30: TextUnit = 30.sp
)

val LocalCustomDimensionsSP = staticCompositionLocalOf { CustomDimensionsSP() }

val MaterialTheme.customDimenSp: CustomDimensionsSP
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomDimensionsSP.current