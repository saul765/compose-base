package com.example.composebase.core.base

interface IBaseAppConfig {

    fun getRemoteConfigDefaultValues(): Map<String, Any>
}