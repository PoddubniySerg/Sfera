package ru.zavod.feature_settings.usecase

import ru.zavod.app_core.model.Configuration
import ru.zavod.feature_settings.di.SettingsDeviceRepository
import javax.inject.Inject

class SaveConfigurationUseCase @Inject constructor(
    private val deviceExtensionRepository: SettingsDeviceRepository
) {

    suspend fun execute(configuration: Configuration?) {
        if (configuration == null) return
        deviceExtensionRepository.saveConfiguration(configuration = configuration)
    }
}