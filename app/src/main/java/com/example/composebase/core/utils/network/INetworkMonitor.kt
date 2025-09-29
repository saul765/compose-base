package com.example.composebase.core.utils.network

import kotlinx.coroutines.flow.Flow

interface INetworkMonitor {

    val isOnline: Flow<Boolean>

    fun isCurrentlyOnline(): Boolean
}