package com.example.composebase.core.webservice.dto.responses

import com.example.composebase.core.model.Other
import com.example.composebase.core.model.Sprite

data class SpriteDTO(val other: OtherDTO?)

fun SpriteDTO.toDomain(): Sprite = Sprite(
    other = other?.toDomain() ?: Other()
)
