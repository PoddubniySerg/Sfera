package ru.zavod.app_di.impl

import kotlinx.coroutines.flow.StateFlow
import ru.zavod.app_core.di.ConfigurationProvider
import ru.zavod.app_core.model.Configuration
import ru.zavod.data_storage.ConfigurationSharedPreferences
import javax.inject.Inject

class ConfigurationProviderImpl @Inject constructor(
    private val configurationProvider: ConfigurationSharedPreferences
) : ConfigurationProvider {
    override val configuration: StateFlow<Configuration?> = configurationProvider.configStateFlow
}