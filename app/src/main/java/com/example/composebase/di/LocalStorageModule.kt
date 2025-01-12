package com.example.composebase.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.composebase.core.preferences.DataStoreHandler
import com.example.composebase.core.utils.coroutines.ICoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object LocalStorageModule {

    private const val PREF_NAME = "composebase_pref"
    val module = module {

        singleOf(LocalStorageModule::provideDataStore)

        singleOf(::DataStoreHandler)

    }

    private fun provideDataStore(
        context: Context,
        dispatcher: ICoroutineContextProvider
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() },
        ),
        migrations = listOf(SharedPreferencesMigration(context, PREF_NAME)),
        scope = CoroutineScope(dispatcher.getIOContext()),
        produceFile = { context.preferencesDataStoreFile(PREF_NAME) }
    )
}