package com.jayden.settingsapplication.app

import android.app.Application
import com.jayden.settingsapplication.app.viewmodel.MainViewModelFactory
import com.jayden.settingsapplication.data.repo.SettingsRepository
import com.jayden.settingsapplication.data.source.SettingsDataStore
import com.jayden.settingsapplication.settings

class MainApplication : Application() {
    lateinit var settingsDataStore: SettingsDataStore
        private set
    lateinit var settingsRepo: SettingsRepository
        private set
    lateinit var viewModelProvider: MainViewModelFactory
        private set

    override fun onCreate() {
        super.onCreate()
        settingsDataStore = SettingsDataStore(applicationContext)
        settingsRepo = SettingsRepository(applicationContext, settingsDataStore)
        viewModelProvider = MainViewModelFactory(settingsRepo)
    }
}