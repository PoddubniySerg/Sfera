package ru.zavod.app_navigation.route

import android.content.res.Resources
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.zavod.app_core.model.Token
import ru.zavod.app_navigation.R
import ru.zavod.app_navigation.di.NavigateApi
import ru.zavod.app_navigation.di.OnboardingParams

internal fun NavGraphBuilder.onboarding(
    navController: NavHostController,
    resources: Resources,
    navigateApi: NavigateApi,
    params: OnboardingParams,
    token: Token?,
    setCurrentRoute: (String?) -> Unit
) {
    val onboardingDestination = resources.getString(R.string.onboarding_destination)
    composable(route = onboardingDestination) {
        setCurrentRoute(onboardingDestination)
        val chatsDestination = stringResource(id = R.string.chats_destination)
        val authRoute = stringResource(id = R.string.auth_destination)
        navigateApi.ToOnboarding(
            params = params,
            start = {
                when (token) {
                    null -> navController.navigate(authRoute)
                    else -> navController.navigate(chatsDestination)
                }
            }
        )
    }
}