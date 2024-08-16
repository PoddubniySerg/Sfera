package ru.zavod.app_navigation.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.zavod.app_navigation.di.NavigateApi

internal fun NavGraphBuilder.settings(
    navigateApi: NavigateApi
) {
    composable(route = Destination.SETTINGS.destination) {
        navigateApi.ToSettings()
    }
}