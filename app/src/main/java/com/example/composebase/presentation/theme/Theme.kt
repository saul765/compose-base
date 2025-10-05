package com.example.composebase.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_on_primary_container,
    onSecondary = md_theme_light_on_secondary,
    surface = Color.White,
    onError = md_theme_light_on_error
)

@Composable
fun ComposeBaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCustomColors provides CustomColors(),
        LocalCustomDimensions provides CustomDimensions()
    ) {
        MaterialTheme(
            colorScheme = LightColorScheme,
            typography = Typography,
            content = content
        )
    }
}