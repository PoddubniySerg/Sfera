package ru.zavod.app_navigation.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.zavod.app_core.model.Token
import ru.zavod.app_navigation.di.NavigateApi
import ru.zavod.app_navigation.di.OnboardingParams

internal fun NavGraphBuilder.onboarding(
    navController: NavHostController,
    navigateApi: NavigateApi,
    params: OnboardingParams,
    token: Token?
) {
    composable(route = Destination.ONBOARDING.destination) {
        navigateApi.ToOnboarding(
            params = params,
            start = {
                when (token) {
                    null -> navController.navigate(route = Destination.AUTH.destination)
                    else -> navController.navigate(route = Destination.CHATS.destination)
                }
            }
        )
    }
}