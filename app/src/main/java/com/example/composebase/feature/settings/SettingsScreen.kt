package com.example.composebase.feature.settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.composebase.core.compose.AppScaffold
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel()
) = AppScaffold {

    Text(text = "Settings Screen")
}
