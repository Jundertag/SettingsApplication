package com.jayden.settingsapplication.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayden.settingsapplication.data.repo.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val repo: SettingsRepository) : ViewModel() {
    val counterFlow = repo.counterFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(1_000),
        0
    )

    fun incrementCounter() {
        viewModelScope.launch {
            repo.incrementCounter()
        }
    }
}