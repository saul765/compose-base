package com.example.composebase.core.utils.coroutines

import kotlin.coroutines.CoroutineContext

interface ICoroutineContextProvider {

    fun getMainContext(): CoroutineContext

    fun getIOContext(): CoroutineContext

    fun getDefaultContext(): CoroutineContext

}