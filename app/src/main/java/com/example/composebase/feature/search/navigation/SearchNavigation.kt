package com.example.composebase.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.composebase.feature.search.SearchScreen
import kotlinx.serialization.Serializable

@Serializable
data object SearchRoute


fun NavController.navigateToSearch(navOptions: NavOptions? = null) =
    navigate(route = SearchRoute, navOptions = navOptions)

fun NavGraphBuilder.searchGraph(
    onBackPressed: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {

    composable<SearchRoute> {
        SearchScreen(onBackPressed = onBackPressed)
    }
    nestedGraphs()

}