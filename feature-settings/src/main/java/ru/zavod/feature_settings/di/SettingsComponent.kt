package ru.zavod.feature_settings.di

import ru.zavod.app_core.model.Configuration
import ru.zavod.app_core.viewmodel.ViewModelFactory
import ru.zavod.feature_settings.viewmodel.SettingsViewModel

interface SettingsComponent {
    fun settingsViewModelFactory(): ViewModelFactory<SettingsViewModel>
}