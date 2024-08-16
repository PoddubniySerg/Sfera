package ru.zavod.feature_settings.usecase

import ru.zavod.feature_settings.di.SettingsDeviceRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val deviceExtensionRepository: SettingsDeviceRepository
) {

    suspend fun execute() {
        deviceExtensionRepository.logout()
    }
}