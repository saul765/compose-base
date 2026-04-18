package com.example.composebase.feature.home

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.example.composebase.core.model.uiModel.PokemonItemUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class HomeUiState(
    val pokemons: Flow<PagingData<PokemonItemUIModel>> = emptyFlow()
)
