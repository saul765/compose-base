package com.example.composebase.core.webservice.dto.responses

import com.example.composebase.core.model.OfficialArtwork
import com.google.gson.annotations.SerializedName

data class OfficialArtworkDTO(@SerializedName("front_default") val frontDefault: String?)

fun OfficialArtworkDTO.toDomain(): OfficialArtwork =
    OfficialArtwork(
        frontDefault = frontDefault.orEmpty()
    )
