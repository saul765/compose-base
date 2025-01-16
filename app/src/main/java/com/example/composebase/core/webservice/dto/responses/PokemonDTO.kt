package com.example.composebase.core.webservice.dto.responses

import com.example.composebase.core.model.Pokemon

data class PokemonDTO(
    val name: String?,
    val url: String?
)

fun PokemonDTO.toDomain(): Pokemon = Pokemon(
    name = name.orEmpty(),
    url = url.orEmpty()
)
