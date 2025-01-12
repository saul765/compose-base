package com.example.composbase

import com.example.composebase.core.base.IBaseAppConfig

object AppConfig: IBaseAppConfig {
    override fun getRemoteConfigDefaultValues(): Map<String, Any> = emptyMap()
}