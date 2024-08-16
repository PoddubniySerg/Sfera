package ru.zavod.app_navigation.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.zavod.app_navigation.di.NavigateApi
import ru.zavod.app_navigation.model.AuthParams

internal fun NavGraphBuilder.auth(
    navController: NavHostController,
    navigateApi: NavigateApi
) {
    composable(route = Destination.AUTH.destination) {
        navigateApi.ToAuth(
            params = AuthParams(
                success = { navController.navigate(route = Destination.CHATS.destination) }
            )
        )
    }
}