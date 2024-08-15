package ru.zavod.app_navigation.route

import android.content.res.Resources
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.zavod.app_navigation.R
import ru.zavod.app_navigation.di.NavigateApi

internal fun NavGraphBuilder.profile(
    resources: Resources,
    navigateApi: NavigateApi,
    setCurrentRoute: (String?) -> Unit
) {
    val profileDestination = resources.getString(R.string.profile_destination)
    composable(route = profileDestination) {
        setCurrentRoute(profileDestination)
        navigateApi.ToAuth()
    }
}