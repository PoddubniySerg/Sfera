package ru.zavod.data_storage.repository

import ru.zavod.app_core.model.Configuration
import ru.zavod.data_storage.ConfigurationSharedPreferences
import ru.zavod.data_storage.DataBaseProvider
import ru.zavod.data_storage.TokensSharedPreferences
import javax.inject.Inject

class SettingsStorage @Inject constructor(
    dataBaseProvider: DataBaseProvider,
    private val tokensSharedPreferences: TokensSharedPreferences,
    private val configurationSharedPreferences: ConfigurationSharedPreferences
) {

    private val dao = dataBaseProvider.getDataBase().chatsDao()

    suspend fun logout() {
        dao.clear()
        tokensSharedPreferences.removeToken()
    }

    suspend fun saveConfiguration(configuration: Configuration) {
        configurationSharedPreferences.saveConfiguration(configuration = configuration)
    }
}