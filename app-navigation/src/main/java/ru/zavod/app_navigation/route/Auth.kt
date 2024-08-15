package ru.zavod.app_navigation.route

import android.content.res.Resources
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.zavod.app_navigation.R
import ru.zavod.app_navigation.di.NavigateApi

internal fun NavGraphBuilder.auth(
    resources: Resources,
    navigateApi: NavigateApi,
    setCurrentRoute: (String?) -> Unit
) {
    val authDestination = resources.getString(R.string.auth_destination)
    composable(route = authDestination) {
        setCurrentRoute(authDestination)
        navigateApi.ToAuth()
    }
}