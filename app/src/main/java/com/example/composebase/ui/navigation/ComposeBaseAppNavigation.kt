package com.example.composebase.ui.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.composebase.feature.home.navigation.HomeBaseRoute
import com.example.composebase.feature.home.navigation.homeGraph
import com.example.composebase.feature.search.navigation.navigateToSearch
import com.example.composebase.feature.search.navigation.searchGraph
import com.example.composebase.feature.settings.navigation.settingsGraph
import com.example.composebase.ui.ComposeBaseNavigationState


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
        homeGraph(onSearchClick = navController::navigateToSearch,
            nestedGraphs = {

                searchGraph(onBackPressed = navController::popBackStack) {}
            }
        )

        settingsGraph(
            nestedGraphs = {
            }
        )

    }
}