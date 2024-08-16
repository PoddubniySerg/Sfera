package ru.zavod.feature_settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.zavod.app_core.di.ConfigurationProvider
import ru.zavod.app_core.model.Configuration
import ru.zavod.app_core.model.LoadStateApp
import ru.zavod.feature_settings.usecase.LogoutUseCase
import ru.zavod.feature_settings.usecase.SaveConfigurationUseCase
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    configurationProvider: ConfigurationProvider,
    private val saveConfigurationUseCase: SaveConfigurationUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val savedConfig = configurationProvider.configuration

    private val loadStateMutableStateFlow =
        MutableStateFlow<LoadStateApp>(value = LoadStateApp.Loading)
    val loadStateStateFlow = loadStateMutableStateFlow.asStateFlow()

    private val configurationMutableStateFlow = MutableStateFlow<Configuration?>(value = null)
    val configurationStateFlow = configurationMutableStateFlow.asStateFlow()

    private val confimButtonEnabledMutableStateFlow = MutableStateFlow(value = true)
    val confimButtonEnabledStateFlow = confimButtonEnabledMutableStateFlow.asStateFlow()

    private val logoutMutableStateFlow = MutableStateFlow(value = false)
    val logoutStateFlow = logoutMutableStateFlow.asStateFlow()

    fun setLogout(openDialog: Boolean) {
        logoutMutableStateFlow.value = openDialog
    }

    init {
        savedConfig.onEach { configuration ->
            setConfiguration(configuration = configuration)
            loadStateMutableStateFlow.value = LoadStateApp.Success
        }.launchIn(scope = viewModelScope)
    }

    fun setConfiguration(configuration: Configuration?) {
        configurationMutableStateFlow.value = configuration
        confimButtonEnabledMutableStateFlow.value = savedConfig.value != configuration
    }

    fun confirm() {
        viewModelScope.launch {
            try {
                loadStateMutableStateFlow.value = LoadStateApp.Loading
                saveConfigurationUseCase.execute(configuration = configurationStateFlow.value)
                loadStateMutableStateFlow.value = LoadStateApp.Success
            } catch (ex: Exception) {
                loadStateMutableStateFlow.value = LoadStateApp.Failed(throwable = ex)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                loadStateMutableStateFlow.value = LoadStateApp.Loading
                logoutUseCase.execute()
                loadStateMutableStateFlow.value = LoadStateApp.Success
            } catch (ex: Exception) {
                loadStateMutableStateFlow.value = LoadStateApp.Failed(throwable = ex)
            }
        }
    }
}