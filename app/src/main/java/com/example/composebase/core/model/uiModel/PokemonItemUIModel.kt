package com.example.composebase.core.model.uiModel

import androidx.compose.runtime.Immutable

@Immutable
data class PokemonItemUIModel(
    val id: Int,
    val imageUrl: String,
    val name: String
)
