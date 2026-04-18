package com.example.composebase.feature.search

import androidx.compose.runtime.Composable
import com.example.composebase.core.base.screen.BaseSearchScreen
import com.example.composebase.feature.home.HomeScreen

@Composable
fun SearchScreen(onBackPressed: () -> Unit) = BaseSearchScreen<SearchViewModel, com.example.composebase.feature.home.HomeUiState>(
    onBack = onBackPressed,
    successView = { data -> HomeScreen({  }) }
)
