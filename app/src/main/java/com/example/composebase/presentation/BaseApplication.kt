package com.example.composebase.presentation

import com.example.composebase.BuildConfig
import com.example.composebase.di.AppModule
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class BaseApplication : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        setUpCrashlytics()
    }

    private fun setUpCrashlytics() {
        FirebaseCrashlytics.getInstance().isCrashlyticsCollectionEnabled = BuildConfig.DEBUG
    }

    private fun initKoin() {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)
            androidContext(this@BaseApplication)
            modules(AppModule.modules)
        }
    }
}
