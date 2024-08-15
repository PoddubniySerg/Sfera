package ru.zavod.app_config

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.zavod.app_core.model.Configuration
import javax.inject.Inject

internal class ConfigViewModel @Inject constructor() : ViewModel() {

    private val configMutableStateFlow = MutableStateFlow<Configuration?>(value = null)
    val configStateFlow = configMutableStateFlow.asStateFlow()

    fun setConfig(configuration: Configuration) {
        configMutableStateFlow.value = configuration
    }
}