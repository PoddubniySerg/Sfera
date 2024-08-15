package ru.zavod.app_navigation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val currentRouteMutableStateFlow = MutableStateFlow<String?>(value = null)
    val currentRouteStateFlow = currentRouteMutableStateFlow.asStateFlow()

    fun setCurrentRoute(route: String?) {
        currentRouteMutableStateFlow.value = route
    }
}