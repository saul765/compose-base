package com.example.composebase.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    Column {
        LaunchedEffect(Unit) {
            viewModel.onEvent(HomeUiEvent.OnStart)

            delay(3000)

            viewModel.onEvent(HomeUiEvent.OnRefresh)

        }

        Text(text = "Home Screen")
    }
}
