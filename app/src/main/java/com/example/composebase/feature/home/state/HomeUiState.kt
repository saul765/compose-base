package com.example.composebase.feature.home.state

import androidx.compose.runtime.Stable
import com.example.composebase.core.model.uiModel.PokemonItemUIModel

@Stable
data class HomeUiState(val pokemons: List<PokemonItemUIModel> = listOf())