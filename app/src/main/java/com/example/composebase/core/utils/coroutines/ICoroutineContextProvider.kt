package com.example.composebase.core.utils.coroutines

import kotlin.coroutines.CoroutineContext

interface ICoroutineContextProvider {

    fun getIOContext(): CoroutineContext

}
