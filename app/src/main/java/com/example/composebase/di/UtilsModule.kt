package com.example.composebase.di

import com.example.composebase.core.utils.bus.IUiEventBus
import com.example.composebase.core.utils.bus.UiEventBus
import com.example.composebase.core.utils.json.JsonUtils
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object UtilsModule {

    val module = module {
        single { JsonUtils }
        singleOf(::UiEventBus)
    }
}