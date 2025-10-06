package com.example.composebase.presentation.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.composebase.presentation.feature.contries.detail.navigation.toCountryDetail
import com.example.composebase.presentation.feature.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data object HomeBaseRoute

fun NavController.navigateToHome(navOptions: NavOptions? = null) =
    navigate(route = HomeRoute, navOptions = navOptions)

fun NavGraphBuilder.homeGraph() {
    navigation<HomeBaseRoute>(startDestination = HomeRoute) {
        composable<HomeRoute> { HomeScreen() }
        toCountryDetail()
    }
}
