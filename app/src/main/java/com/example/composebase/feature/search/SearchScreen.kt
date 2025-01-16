package com.example.composebase.feature.search

import androidx.compose.runtime.Composable
import com.example.composebase.core.base.screen.BaseSearchScreen
import com.example.composebase.feature.home.state.HomeUiState
import com.example.composebase.feature.home.views.HomeView

@Composable
fun SearchScreen(onBackPressed: () -> Unit) = BaseSearchScreen<SearchViewModel, HomeUiState>(
    onBack = onBackPressed,
    successView = { data ->

        HomeView(data)
    })