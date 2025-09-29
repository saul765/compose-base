package com.example.composebase.core.base.screen


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
inline fun BaseScreen(
    modifier: Modifier = Modifier,
    noinline topBar: @Composable () -> Unit = {},
    noinline floatingActionButton: @Composable () -> Unit = {},
    noinline snackBarHost: @Composable () -> Unit = {},
    crossinline content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        floatingActionButton = floatingActionButton,
        snackbarHost = snackBarHost,
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .consumeWindowInsets(paddingValues)
                    .padding(paddingValues)
            ) {
                content()
            }
        }
    )
}