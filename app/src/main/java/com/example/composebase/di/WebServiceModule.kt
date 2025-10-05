package com.example.composebase.di

import com.example.composebase.data.api.CountryAPI
import com.example.composebase.data.api.ICountryAPI
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
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

        singleOf(::CountryAPI).bind(ICountryAPI::class)
    }
}