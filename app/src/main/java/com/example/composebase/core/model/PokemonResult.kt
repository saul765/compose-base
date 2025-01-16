package com.example.composebase.core.model

data class PokemonResult(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Pokemon>
)