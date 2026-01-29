package com.jayden.settingsapplication.data.source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.google.protobuf.copy
import com.jayden.settingsapplication.Settings
import com.jayden.settingsapplication.copy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Settings> by dataStore(
    fileName = "settings.pb",
    serializer = SettingsSerializer
)

class SettingsDataStore(
    private val context: Context
) {
    fun counterFlow(): Flow<Int> = context.dataStore.data.map { settings ->
        settings.exampleCounter
    }

    suspend fun incrementCounter() {
        context.dataStore.updateData { settings ->
            settings.copy { exampleCounter += 1 }
        }
    }
}