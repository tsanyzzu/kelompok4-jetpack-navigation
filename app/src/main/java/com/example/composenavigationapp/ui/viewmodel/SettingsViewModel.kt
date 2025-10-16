package com.example.composenavigationapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenavigationapp.ui.data.SettingsDataStore
import com.example.composenavigationapp.ui.data.ThemePreference
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val settingsStore = SettingsDataStore(application.applicationContext)

    val themePreference: StateFlow<ThemePreference> = settingsStore.themePreferenceFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ThemePreference.SYSTEM
        )

    val notificationsEnabled: StateFlow<Boolean> = settingsStore.notificationsEnabledFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = true
        )

    fun updateThemePreference(newPreference: ThemePreference) {
        viewModelScope.launch {
            settingsStore.setThemePreference(newPreference)
        }
    }

    fun updateNotificationsEnabled(enabled: Boolean) {
        viewModelScope.launch {
            settingsStore.setNotificationsEnabled(enabled)
        }
    }
}


