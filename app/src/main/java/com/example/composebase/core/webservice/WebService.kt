package com.example.composebase.core.webservice

import android.content.Context
import com.example.composebase.core.webservice.utils.network.IOkHttpClient
import com.example.composebase.R
import com.example.composebase.core.webservice.apis.IPokemonApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebService : KoinComponent {

    private val context by inject<Context>()

    private val okHttpClient by inject<IOkHttpClient>()


    fun createPokemonApi(): IPokemonApi {
        val client = okHttpClient.create()
        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.pokemon_base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(IPokemonApi::class.java)
    }

}