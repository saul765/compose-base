package com.example.composebase.di

import com.example.composebase.core.utils.network.INetworkMonitor
import com.example.composebase.core.utils.network.NetworkMonitor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object NetworkModule {

    val module = module {

        singleOf(::NetworkMonitor).bind(INetworkMonitor::class)

    }
}