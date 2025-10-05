package com.example.composebase.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebase.ui.theme.ComposeBaseTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {

    HomeScreenContent()
}

@Composable
private fun HomeScreenContent(onEvent: (HomeUiEvent) -> Unit = {}) {
    Column {
        LaunchedEffect(Unit) {
            onEvent(HomeUiEvent.OnStart)
        }

        Text(text = "Home Screen")
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    ComposeBaseTheme {
        HomeScreen()
    }
}