package ru.zavod.data_storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.zavod.app_core.model.Configuration
import javax.inject.Inject

class ConfigurationSharedPreferences @Inject constructor(context: Context) {

    companion object {
        private const val CONFIG_PREFERENCES_NAME = "config"
        private const val THEME_KEY = "theme"
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = CONFIG_PREFERENCES_NAME)
    }

    private val context = context.applicationContext

    private val configMutableStateFlow = MutableStateFlow<Configuration>(value = Configuration())
    val configStateFlow = configMutableStateFlow.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.data.collect { preferences ->
                val themeKey = stringPreferencesKey(name = THEME_KEY)
                val theme = preferences[themeKey]?.let { nameTheme ->
                    Configuration.ApplicationTheme.entries.firstOrNull { it.name == nameTheme }
                }
                theme?.let {
                    val config = configStateFlow.value
                    configMutableStateFlow.value = config.copy(theme = it)
                }
            }
        }
    }

    suspend fun saveConfiguration(configuration: Configuration) {
        CoroutineScope(Dispatchers.IO).launch {
            val themeKey = stringPreferencesKey(name = THEME_KEY)
            context.dataStore.edit { preferences ->
                preferences[themeKey] = configuration.theme.name
            }
        }
    }
}