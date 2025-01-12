package com.example.composebase.feature.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.composebase.core.base.screen.BaseScreen


@Composable
fun HomeScreen() =
    BaseScreen<HomeViewModel>() { viewModel ->

        Text(text = "Home Screen")
    }