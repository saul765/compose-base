package com.example.composebase.presentation.feature.settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.composebase.core.base.screen.BaseScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = koinViewModel()) {
    BaseScreen {
        Text(text = "Settings Screen")
    }
}