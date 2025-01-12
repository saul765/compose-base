package com.example.composebase.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.composebase.feature.settings.SettingsScreen
import kotlinx.serialization.Serializable


@Serializable
data object SettingsRoute

@Serializable
data object SettingsBaseRoute


fun NavController.navigateToSettings(navOptions: NavOptions? = null) =
    navigate(route = SettingsRoute, navOptions = navOptions)

fun NavGraphBuilder.settingsGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation<SettingsBaseRoute>(startDestination = SettingsRoute) {
        composable<SettingsRoute> {
            SettingsScreen()
        }
        nestedGraphs()
    }
}