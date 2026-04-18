package com.example.composebase.core.utils.coroutines

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class CoroutineContextProvider : ICoroutineContextProvider {
    override fun getIOContext(): CoroutineContext = Dispatchers.IO
}
