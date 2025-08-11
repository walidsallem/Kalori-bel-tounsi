package com.kalori.tounsi.data.prefs

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

object Keys {
    val FIRST_RUN = booleanPreferencesKey("first_run")
    val GOAL = stringPreferencesKey("goal") // lose | maintain | gain
    val MODE = stringPreferencesKey("mode") // fasting_16_8 | normal_3
    val WATER_TARGET = intPreferencesKey("water_target_ml") // default 2000
    val WATER_TODAY = intPreferencesKey("water_today_ml")
}

class Prefs(private val ctx: Context) {
    val store = ctx.dataStore

    suspend fun isFirstRun() = store.data.map { it[Keys.FIRST_RUN] ?: true }.first()
    suspend fun setFirstRun(v: Boolean) = store.edit { it[Keys.FIRST_RUN] = v }

    suspend fun setGoal(g: String) = store.edit { it[Keys.GOAL] = g }
    val goal = store.data.map { it[Keys.GOAL] ?: "maintain" }

    suspend fun setMode(m: String) = store.edit { it[Keys.MODE] = m }
    val mode = store.data.map { it[Keys.MODE] ?: "normal_3" }

    val waterTarget = store.data.map { it[Keys.WATER_TARGET] ?: 2000 }
    suspend fun setWaterTarget(v: Int) = store.edit { it[Keys.WATER_TARGET] = v }

    val waterToday = store.data.map { it[Keys.WATER_TODAY] ?: 0 }
    suspend fun addWater(ml: Int) = store.edit { it[Keys.WATER_TODAY] = (it[Keys.WATER_TODAY] ?: 0) + ml }
}
