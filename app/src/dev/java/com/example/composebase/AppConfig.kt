package com.example.composebase

import com.example.composebase.core.base.IBaseAppConfig

object AppConfig: IBaseAppConfig {
    override fun getRemoteConfigDefaultValues(): Map<String, Any> = emptyMap()
}