package com.example.composebase.di

import com.example.composebase.core.utils.coroutines.CoroutineContextProvider
import com.example.composebase.core.utils.coroutines.ICoroutineContextProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object CoroutinesModule {

    val module = module {
        singleOf(::CoroutineContextProvider).bind(ICoroutineContextProvider::class)
    }
}