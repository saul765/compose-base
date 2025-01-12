package com.example.composebase.di

import com.example.composebase.core.utils.json.JsonUtils
import org.koin.dsl.module

object UtilsModule {

    val module = module {

        single { JsonUtils }
    }
}