package ru.zavod.app_core.di

import kotlinx.coroutines.flow.StateFlow
import ru.zavod.app_core.model.Configuration

interface ConfigurationProvider {
    val configuration: StateFlow<Configuration?>
}