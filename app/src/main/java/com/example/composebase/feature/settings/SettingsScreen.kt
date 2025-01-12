package com.example.composebase.feature.settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.composebase.core.base.screen.BaseScreen

@Composable
fun SettingsScreen() = BaseScreen<SettingsViewModel> { viewModel ->

    Text(text = "Settings Screen")
}