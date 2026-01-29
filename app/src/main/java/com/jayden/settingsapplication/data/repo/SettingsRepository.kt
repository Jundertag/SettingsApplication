package com.jayden.settingsapplication.data.repo

import android.content.Context
import androidx.datastore.dataStore
import com.jayden.settingsapplication.data.source.SettingsDataStore

class SettingsRepository(private val ctx: Context, private val dataStore: SettingsDataStore) {
    val counterFlow = dataStore.counterFlow()
    suspend fun incrementCounter() {
        dataStore.incrementCounter()
    }
}