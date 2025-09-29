package com.example.composebase.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    Column {
        Text(text = "Home Screen")
    }
}
