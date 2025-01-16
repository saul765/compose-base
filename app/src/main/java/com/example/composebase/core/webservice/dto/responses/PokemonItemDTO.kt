package com.example.composebase.core.webservice.dto.responses

import com.example.composebase.core.ZERO_INTEGER
import com.example.composebase.core.model.PokemonItem
import com.example.composebase.core.model.Sprite

data class PokemonItemDTO(
    val id: Int?,
    val sprites: SpriteDTO?,
    val name: String?
)

fun PokemonItemDTO.toDomain(): PokemonItem =
    PokemonItem(
        id = id ?: ZERO_INTEGER,
        sprites = sprites?.toDomain() ?: Sprite(),
        name = name.orEmpty()
    )
