package com.example.composebase.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.composebase.core.utils.json.JsonUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreHandler(
    val dataStore: DataStore<Preferences>,
    val jsonUtils: JsonUtils
) {
    companion object {
        val USER_DATA = stringPreferencesKey("user_data")
    }

    inline fun <reified T> getValue(key: Preferences.Key<String>, default: T): Flow<T> {
        return dataStore.data.map { preferences ->
            val jsonString = preferences[key]
            jsonUtils.decodeJson(jsonString, default)
        }
    }

    suspend inline fun <reified T> saveValue(key: Preferences.Key<String>, value: T) {

        val jsonString = jsonUtils.encodeJson(value)
        dataStore.edit { preferences ->
            preferences[key] = jsonString
        }
    }

}