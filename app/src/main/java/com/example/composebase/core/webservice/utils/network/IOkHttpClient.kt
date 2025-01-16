package com.example.composebase.core.webservice.utils.network

import okhttp3.OkHttpClient

interface IOkHttpClient {
    fun create(): OkHttpClient
}