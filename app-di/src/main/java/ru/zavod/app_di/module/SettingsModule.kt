package ru.zavod.app_di.module

import dagger.Module
import dagger.Provides
import ru.zavod.app_di.impl.SettingsDeviceRepositoryImpl
import ru.zavod.feature_settings.di.SettingsDeviceRepository

@Module
class SettingsModule {

    @Provides
    fun providesDeviceSettingsRepository(
        storage: SettingsDeviceRepositoryImpl
    ): SettingsDeviceRepository {
        return storage
    }
}