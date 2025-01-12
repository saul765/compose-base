package com.example.composebase.core.design_system

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag


@Composable
fun <T> BottomBarScaffold(
    modifier: Modifier = Modifier,
    navigationItems: List<T>,
    shouldShowNavigationBar: Boolean,
    navigationItemTitle: @Composable (item: T, isSelected: Boolean) -> Unit,
    navigationItemIcon: @Composable (item: T, isSelected: Boolean) -> Unit,
    isItemSelected: @Composable (item: T) -> Boolean,
    onNavigationItemClick: (item: T) -> Unit,
    topBar: (@Composable () -> Unit)? = null,
    snackbarHost: @Composable () -> Unit,
    content: @Composable (padding: PaddingValues) -> Unit,
) {


    Box(modifier = modifier) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = topBar.takeIf { it != null }?.let { { it.invoke() } } ?: {},
            snackbarHost = snackbarHost,
            bottomBar = {
                AnimatedVisibility(visible = shouldShowNavigationBar) {
                    MaterialTheme() {
                        NavigationBar(
                            modifier = Modifier.testTag("adaptiveScaffold:navigationBar"),
                        ) {
                            navigationItems.forEach { item ->
                                NavigationBarItem(
                                    alwaysShowLabel = true,
                                    label = { navigationItemTitle(item, isItemSelected(item)) },
                                    icon = { navigationItemIcon(item, isItemSelected(item)) },
                                    selected = isItemSelected(item),
                                    onClick = { onNavigationItemClick(item) },
                                )
                            }
                        }
                    }
                }
            },
        ) { padding ->
            content(padding)
        }
    }
}