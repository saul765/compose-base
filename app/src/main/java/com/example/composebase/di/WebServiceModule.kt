package com.example.composebase.di

import com.ba.pokedex.webservice.utils.network.LoggingInterceptor
import com.ba.pokedex.webservice.utils.network.OkHttpClient
import com.example.composebase.core.webservice.WebService
import com.example.composebase.core.webservice.apis.IPokemonApi
import com.example.composebase.core.webservice.utils.network.IOkHttpClient
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

        single<IOkHttpClient> { OkHttpClient(get()) }

        single { LoggingInterceptor }

        single<IPokemonApi> { WebService.createPokemonApi() }

    }
}