package com.example.converterapp.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStorePreference @Inject constructor(@ApplicationContext context: Context)  {
     private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
     private val dataStore = context.dataStore

    companion object {
        val expiryTime = longPreferencesKey("EXPIRY_TIME")
    }

    suspend fun saveExpiryTime(time :Long) {
        dataStore.edit { settings ->
            settings[expiryTime] = time
        }
    }

    suspend fun getExpiryTime():Flow<Long>{
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map { settings ->
           val value =  settings[expiryTime] ?: 0L
                value
        }
    }
}

