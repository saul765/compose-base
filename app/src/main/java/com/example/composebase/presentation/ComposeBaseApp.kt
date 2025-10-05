package com.example.composebase.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.composebase.core.design_system.BottomBarScaffold
import com.example.composebase.core.design_system.SnackBar
import com.example.composebase.core.utils.events.LocalSnackBarState
import com.example.composebase.core.utils.events.UiEventHandler
import com.example.composebase.presentation.navigation.ComposeBaseAppNavHost
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.KClass

@Composable
fun ComposeBaseApp(
    navigationState: ComposeBaseNavigationState,
    viewModel: MainViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.onEvent(MainActivityUiEvent.OnStart)
    }

    BottomBarScaffold(
        navigationItems = navigationState.topLevelDestinations,
        navigationItemTitle = { item, _ -> Text(text = stringResource(item.iconText)) },
        navigationItemIcon = { item, isSelected ->
            Icon(
                imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                contentDescription = null
            )
        },
        isItemSelected = { item ->
            navigationState.currentDestination?.isRouteInHierarchy(item.route) ?: false
        },
        shouldShowNavigationBar =
            navigationState.topLevelDestinations.any {
                navigationState.currentDestination?.isRouteInHierarchy(it.route) ?: false
            },
        onNavigationItemClick = navigationState::navigateToTopLevelDestination,
        snackbarHost = {
            SnackbarHost(LocalSnackBarState.current) {
                SnackBar(data = it)
            }
        }
    ) { paddingValues ->
        UiEventHandler(viewModel.uiEvents) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .consumeWindowInsets(paddingValues)
            ) {
                ComposeBaseAppNavHost(
                    modifier = Modifier.imePadding(),
                    navController = navigationState.navController
                )
            }
        }
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>): Boolean =
    this?.hierarchy?.any { it.hasRoute(route) } ?: false