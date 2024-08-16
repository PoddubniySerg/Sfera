package ru.zavod.app_navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.zavod.app_core.model.Token
import ru.zavod.app_navigation.di.NavigateApi
import ru.zavod.app_navigation.di.OnboardingParams
import ru.zavod.app_navigation.route.Destination
import ru.zavod.app_navigation.route.auth
import ru.zavod.app_navigation.route.chats
import ru.zavod.app_navigation.route.onboarding
import ru.zavod.app_navigation.route.profile
import ru.zavod.app_navigation.route.settings

@Composable
internal fun AppNavHost(
    navController: NavHostController,
    navigateApi: NavigateApi,
    onboardingParams: OnboardingParams,
    token: Token?
) {
    NavHost(
        navController = navController,
        startDestination = Destination.ONBOARDING.destination,
        modifier = Modifier.fillMaxSize()
    ) {

        onboarding(
            navController = navController,
            navigateApi = navigateApi,
            params = onboardingParams,
            token = token
        )

        auth(navController = navController, navigateApi = navigateApi)

        chats(navigateApi = navigateApi)

        profile(navigateApi = navigateApi)

        settings(navigateApi = navigateApi)
    }
}