package com.example.composebase.core.webservice.dto.responses

import com.example.composebase.core.model.OfficialArtwork
import com.example.composebase.core.model.Other
import com.google.gson.annotations.SerializedName

data class OtherDTO(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtworkDTO?
)

fun OtherDTO.toDomain(): Other =
    Other(
        officialArtwork = officialArtwork?.toDomain() ?: OfficialArtwork()
    )