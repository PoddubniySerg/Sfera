package ru.zavod.app_navigation.route

import android.content.res.Resources
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.zavod.app_navigation.R
import ru.zavod.app_navigation.di.NavigateApi
import ru.zavod.app_navigation.di.OnboardingParams

internal fun NavGraphBuilder.onboarding(
    navController: NavHostController,
    resources: Resources,
    navigateApi: NavigateApi,
    params: OnboardingParams,
    setCurrentRoute: (String?) -> Unit
) {
    val onboardingDestination = resources.getString(R.string.onboarding_destination)
    composable(route = onboardingDestination) {
        setCurrentRoute(onboardingDestination)
        val chatsDestination = stringResource(id = R.string.chats_destination)
        navigateApi.ToOnboarding(
            params = params,
            start = { navController.navigate(chatsDestination) }
        )
    }
}