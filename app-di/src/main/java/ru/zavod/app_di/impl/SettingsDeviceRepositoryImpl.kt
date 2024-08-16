package ru.zavod.app_di.impl

import ru.zavod.app_core.model.Configuration
import ru.zavod.data_storage.repository.SettingsStorage
import ru.zavod.feature_settings.di.SettingsDeviceRepository
import javax.inject.Inject

class SettingsDeviceRepositoryImpl @Inject constructor(
    private val storage: SettingsStorage
) : SettingsDeviceRepository {

    override suspend fun logout() {
        storage.logout()
    }

    override suspend fun saveConfiguration(configuration: Configuration) {
        storage.saveConfiguration(configuration = configuration)
    }
}