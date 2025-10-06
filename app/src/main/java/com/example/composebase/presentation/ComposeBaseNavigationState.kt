package com.example.composebase.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.composebase.core.model.enums.TopLevelDestination
import com.example.composebase.presentation.feature.home.navigation.HomeRoute
import com.example.composebase.presentation.feature.home.navigation.navigateToHome
import com.example.composebase.presentation.feature.settings.navigation.SettingsRoute
import com.example.composebase.presentation.feature.settings.navigation.navigateToSettings

data class ComposeBaseNavigationState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.HOME -> {
                if (!navController.popBackStack(HomeRoute, false))
                    navController.navigateToHome(topLevelNavOptions)
            }

            TopLevelDestination.SETTINGS -> {
                if (!navController.popBackStack(SettingsRoute, false)
                ) navController.navigateToSettings()
            }
        }
    }
}


@Composable
fun rememberComposeBaseNavigationState(
    navController: NavHostController = rememberNavController(),
): ComposeBaseNavigationState = remember(navController) {
    ComposeBaseNavigationState(navController = navController)
}
