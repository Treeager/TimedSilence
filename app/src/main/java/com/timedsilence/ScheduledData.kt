package com.timedsilence

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("schedule")

class ScheduledData(private val context: Context) {
    companion object {
        val IS_SCHEDULED_KEY = booleanPreferencesKey("is_schedule")
    }

    fun getIsScheduled(): Flow<Boolean> {
        return context.dataStore.data
            .map { preferences ->
                preferences[IS_SCHEDULED_KEY] ?: false
            }
    }

    // Save into datastore

    suspend fun changeIsScheduled(bool: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_SCHEDULED_KEY] = bool
        }
    }
}