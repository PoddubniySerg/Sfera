package ru.zavod.app_navigation.viewmodel

import androidx.lifecycle.ViewModel
import ru.zavod.app_core.di.ConfigurationProvider
import ru.zavod.app_navigation.di.NavigateApi
import ru.zavod.app_navigation.di.TokenProvider
import javax.inject.Inject

class NavigationViewModel @Inject constructor(
    tokenProvider: TokenProvider,
    configurationProvider: ConfigurationProvider,
    val navigateApi: NavigateApi
) : ViewModel() {

    val tokenStateFlow = tokenProvider.token
    val config = configurationProvider.configuration
}