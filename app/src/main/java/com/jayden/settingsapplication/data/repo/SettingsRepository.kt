package com.jayden.settingsapplication.data.repo

import android.content.Context
import androidx.datastore.dataStore
import com.jayden.settingsapplication.Settings
import com.jayden.settingsapplication.data.source.SettingsDataStore

class SettingsRepository(private val ctx: Context, private val dataStore: SettingsDataStore) {
    val settingsFlow = dataStore.settingsFlow()

    suspend fun updateSettings(transform: ((Settings) -> Settings)) {
        dataStore.updateData(transform)
    }
}