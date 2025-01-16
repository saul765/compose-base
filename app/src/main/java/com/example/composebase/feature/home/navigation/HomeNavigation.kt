package com.example.composebase.feature.home.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.composebase.feature.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data object HomeBaseRoute


fun NavController.navigateToHome(navOptions: NavOptions? = null) =
    navigate(route = HomeRoute, navOptions = navOptions)

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.homeGraph(
    onSearchClick: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation<HomeBaseRoute>(startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(onSearchClick = onSearchClick)
        }
        nestedGraphs()
    }
}