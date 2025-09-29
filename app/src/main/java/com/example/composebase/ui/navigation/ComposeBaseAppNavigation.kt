package com.example.composebase.ui.navigation


import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.composebase.feature.home.navigation.HomeBaseRoute
import com.example.composebase.feature.home.navigation.homeGraph
import com.example.composebase.feature.settings.navigation.settingsGraph


@Composable
fun ComposeBaseAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeBaseRoute,
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