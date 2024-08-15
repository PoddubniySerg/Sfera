package ru.zavod.app_navigation.route

import android.content.res.Resources
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.zavod.app_navigation.R
import ru.zavod.app_navigation.di.NavigateApi

internal fun NavGraphBuilder.settings(
    resources: Resources,
    navigateApi: NavigateApi,
    setCurrentRoute: (String?) -> Unit
) {
    val settingsDestination = resources.getString(R.string.settings_destination)
    composable(route = settingsDestination) {
        setCurrentRoute(settingsDestination)
        navigateApi.ToSettings()
    }
}