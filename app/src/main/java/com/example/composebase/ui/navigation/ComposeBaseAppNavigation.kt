package com.example.composebase.ui.navigation


import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.composebase.feature.home.navigation.HomeBaseRoute
import com.example.composebase.feature.home.navigation.homeGraph
import com.example.composebase.feature.settings.navigation.settingsGraph
import com.example.composebase.ui.ComposeBaseNavigationState


@Composable
fun ComposeBaseAppNavHost(
    appState: ComposeBaseNavigationState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HomeBaseRoute,
        modifier = modifier,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() }
    ) {
        homeGraph(
            nestedGraphs = {
            }
        )

        settingsGraph(
            nestedGraphs = {
            }
        )

    }
}