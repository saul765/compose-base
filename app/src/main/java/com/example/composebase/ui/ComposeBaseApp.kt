package com.example.composebase.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.composebase.R
import com.example.composebase.core.design_system.BottomBarScaffold
import com.example.composebase.core.design_system.SnackBar
import com.example.composebase.core.model.AppUiState
import com.example.composebase.ui.navigation.ComposeBaseAppNavHost
import kotlin.reflect.KClass

@Composable
fun ComposeBaseApp(navigationState: ComposeBaseNavigationState, uiState: AppUiState) {

    val snackBarHostState = remember { SnackbarHostState() }

    val offlineModeMessage = stringResource(R.string.offline_mode_message)

    val onlineModeMessage = stringResource(R.string.online_mode_message)

    var wasOffline by remember { mutableStateOf(false) }


    LaunchedEffect(uiState.isOnline) {
        if (!uiState.isOnline) {
            wasOffline = true
            snackBarHostState.showSnackbar(
                message = offlineModeMessage,
                duration = SnackbarDuration.Long
            )
        } else if (wasOffline) {
            wasOffline = false
            snackBarHostState.showSnackbar(
                message = onlineModeMessage,
                duration = SnackbarDuration.Long
            )
        }
    }

    BottomBarScaffold(
        navigationItems = navigationState.topLevelDestinations,
        navigationItemTitle = { item, _ -> Text(text = stringResource(item.iconText)) },
        navigationItemIcon = { item, isSelected ->
            Icon(
                imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                contentDescription = null,
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
            SnackbarHost(snackBarHostState) {
                SnackBar(data = it)
            }
        }

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues)
        ) {
            ComposeBaseAppNavHost(
                modifier = Modifier.imePadding(),
                appState = navigationState
            )
        }
    }


}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>): Boolean =
    this?.hierarchy?.any { it.hasRoute(route) } ?: false