package com.jayden.settingsapplication.data.source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.jayden.settingsapplication.Settings
import kotlinx.coroutines.flow.Flow

val Context.dataStore: DataStore<Settings> by dataStore(
    fileName = "settings.pb",
    serializer = SettingsSerializer
)

class SettingsDataStore(
    private val context: Context
) {
    fun settingsFlow(): Flow<Settings> = context.dataStore.data

    suspend fun updateData(transform: ((Settings) -> Settings)) {
        context.dataStore.updateData(transform)
    }
}