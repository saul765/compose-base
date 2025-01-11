package com.example.composebase.di

import kotlinx.serialization.json.Json
import org.koin.dsl.module

object WebServiceModule {

    val module = module {

        single {
            Json {
                ignoreUnknownKeys = true
                encodeDefaults = true
                explicitNulls = false
            }
        }


    }
}