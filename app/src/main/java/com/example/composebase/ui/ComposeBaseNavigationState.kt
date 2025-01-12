package com.example.composebase.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.composebase.core.model.enums.TopLevelDestination
import com.example.composebase.feature.home.navigation.navigateToHome
import com.example.composebase.feature.settings.navigation.navigateToSettings


data class ComposeBaseNavigationState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {

                popUpTo(navController.graph.id) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true

            }

            when (topLevelDestination) {
                TopLevelDestination.HOME -> navController.navigateToHome(topLevelNavOptions)

                TopLevelDestination.SETTINGS -> navController.navigateToSettings(topLevelNavOptions)
            }
        }
    }
}


@Composable
fun rememberComposeBaseNavigationState(
    navController: NavHostController = rememberNavController(),
): ComposeBaseNavigationState = remember(
    navController
) {
    ComposeBaseNavigationState(
        navController = navController
    )
}
