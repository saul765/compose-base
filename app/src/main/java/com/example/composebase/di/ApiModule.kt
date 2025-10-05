package com.example.composebase.di

import com.apollographql.apollo.ApolloClient
import org.koin.dsl.module

object ApiModule {

    val module = module {
        single {
            ApolloClient.Builder()
                .serverUrl("https://countries.trevorblades.com/graphql")
                .build()
        }
    }
}