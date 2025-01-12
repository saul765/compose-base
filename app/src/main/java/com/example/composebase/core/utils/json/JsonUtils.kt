package com.example.composebase.core.utils.json

import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object JsonUtils : KoinComponent {

    val json by inject<Json>()

    inline fun <reified T> decodeJson(jsonString: String?, default: T): T =
        jsonString?.let { json.decodeFromString(it) } ?: default

    inline fun <reified T> encodeJson(data: T): String {
        val serializer: SerializationStrategy<T> = json.serializersModule.serializer()
        return json.encodeToString(serializer, data)
    }
}