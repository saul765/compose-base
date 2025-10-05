package com.example.composebase.presentation.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composebase.core.utils.events.UiEventHandler
import com.example.composebase.presentation.theme.ComposeBaseTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    UiEventHandler(viewModel.uiEvents) {
        HomeScreenContent(
            uiState = uiState,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
private fun HomeScreenContent(uiState: HomeUiState, onEvent: (HomeUiEvent) -> Unit = {}) {
    LaunchedEffect(Unit) {
        onEvent(HomeUiEvent.OnStart)
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenContentPreview() {
    ComposeBaseTheme {
        HomeScreenContent(uiState = HomeUiState())
    }
}
