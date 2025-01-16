package com.example.composebase.core.model

import com.example.composebase.core.EMPTY_CHARACTER
import com.example.composebase.core.ZERO_INTEGER
import com.example.composebase.core.database.entity.PokemonEntity
import com.example.composebase.core.model.uiModel.PokemonItemUIModel

data class PokemonItem(
    val id: Int = ZERO_INTEGER,
    val sprites: Sprite = Sprite(),
    val name: String = EMPTY_CHARACTER
)


fun PokemonItem.toUIModel(): PokemonItemUIModel = PokemonItemUIModel(
    id = id,
    imageUrl = sprites.other.officialArtwork.frontDefault,
    name = name
)

fun PokemonItem.toEntity(): PokemonEntity = PokemonEntity(
    pokedexNumber = id,
    name = name,
    imageUrl = sprites.other.officialArtwork.frontDefault
)