package ru.zavod.app_navigation.di

import kotlinx.coroutines.flow.StateFlow
import ru.zavod.app_core.model.Token

interface TokenProvider {
    val token: StateFlow<Token?>
}