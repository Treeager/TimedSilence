package com.timedsilence

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.math.min

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("schedule")

class ScheduledData(private val context: Context) {
    companion object {
        val IS_SCHEDULED_KEY = booleanPreferencesKey("is_scheduled")
        val HOUR_OF_DAY_KEY = intPreferencesKey("hour_of_day")
        val MINUTE_KEY = intPreferencesKey("minute")
    }

    fun getMinute(): Flow<Int> {
        return context.dataStore.data
            .map { preferences ->
                preferences[MINUTE_KEY] ?: 0
            }
    }
    fun getHourOfDay(): Flow<Int> {
        return context.dataStore.data
            .map { preferences ->
                preferences[HOUR_OF_DAY_KEY] ?: 0
            }
    }
    fun getIsScheduled(): Flow<Boolean> {
        return context.dataStore.data
            .map { preferences ->
                preferences[IS_SCHEDULED_KEY] ?: false
            }
    }
    suspend fun changeMinute(minute: Int) {
        context.dataStore.edit { preferences ->
            preferences[MINUTE_KEY] = minute
        }
    }

    suspend fun changeHourOfDay(hour: Int) {
        context.dataStore.edit { preferences ->
            preferences[HOUR_OF_DAY_KEY] = hour
        }
    }

    // Save into datastore
    suspend fun changeIsScheduled(bool: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_SCHEDULED_KEY] = bool
        }
    }


}