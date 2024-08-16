package ru.zavod.feature_settings.di

import ru.zavod.app_core.model.Configuration

interface SettingsDeviceRepository {
    suspend fun logout()
    suspend fun saveConfiguration(configuration: Configuration)
}