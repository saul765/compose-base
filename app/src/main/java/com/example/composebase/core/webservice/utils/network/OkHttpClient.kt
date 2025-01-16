package com.ba.pokedex.webservice.utils.network

import com.example.composebase.core.webservice.utils.network.IOkHttpClient
import okhttp3.OkHttpClient
import org.koin.core.component.KoinComponent
import java.util.concurrent.TimeUnit

class OkHttpClient(private val loggingInterceptor: LoggingInterceptor) : IOkHttpClient,
    KoinComponent {

    override fun create(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }


}