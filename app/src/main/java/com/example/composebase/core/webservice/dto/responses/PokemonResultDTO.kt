package com.example.composebase.core.webservice.dto.responses

import com.example.composebase.core.ZERO_INTEGER
import com.example.composebase.core.model.Pokemon
import com.example.composebase.core.model.PokemonResult

data class PokemonResultDTO(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<PokemonDTO?>? = emptyList()
)

fun PokemonResultDTO.toDomain(): PokemonResult = PokemonResult(
    count = count ?: ZERO_INTEGER,
    next = next.orEmpty(),
    previous = previous.orEmpty(),
    results = results?.map { it?.toDomain() ?: Pokemon() } ?: emptyList()
)
