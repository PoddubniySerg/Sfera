package ru.zavod.app_navigation.route

import android.content.res.Resources
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.zavod.app_navigation.R
import ru.zavod.app_navigation.di.NavigateApi

internal fun NavGraphBuilder.chats(
    resources: Resources,
    navigateApi: NavigateApi,
    setCurrentRoute: (String?) -> Unit
) {
    val chatsDestination = resources.getString(R.string.chats_destination)
    composable(route = chatsDestination) {
        setCurrentRoute(chatsDestination)
        navigateApi.ToChats()
    }
}