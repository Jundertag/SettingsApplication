package com.jayden.settingsapplication.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayden.settingsapplication.Settings
import com.jayden.settingsapplication.data.repo.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val repo: SettingsRepository) : ViewModel() {
    data class SettingsState(val tracking: Boolean)

    val settingsUiState = repo.settingsFlow.map { settings ->
        SettingsState(tracking = settings.tracking)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        SettingsState(false)
    )

    fun setTracking(to: Boolean) {
        Log.d(TAG, "calling repo updateSettings with setTracking($to)")
        viewModelScope.launch {
            repo.updateSettings { it.toBuilder().setTracking(to).build() }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}