package com.example.composebase.presentation.feature.home

import androidx.compose.runtime.Stable
import com.example.composebase.domain.model.Country

@Stable
data class HomeUiState(
    val countries: List<Country> = emptyList()
)
