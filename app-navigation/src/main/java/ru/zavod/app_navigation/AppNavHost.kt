package ru.zavod.app_navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.zavod.app_core.model.Token
import ru.zavod.app_navigation.di.NavigateApi
import ru.zavod.app_navigation.di.OnboardingParams
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
    token: Token?,
    setCurrentRoute: (String?) -> Unit
) {
    val resources = LocalContext.current.resources
    NavHost(
        navController = navController,
        startDestination = stringResource(R.string.onboarding_destination),
        modifier = Modifier.fillMaxSize()
    ) {

        onboarding(
            navController = navController,
            resources = resources,
            navigateApi = navigateApi,
            params = onboardingParams,
            token = token,
            setCurrentRoute = setCurrentRoute
        )

        auth(
            resources = resources,
            navigateApi = navigateApi,
            setCurrentRoute = setCurrentRoute
        )

        chats(
            resources = resources,
            navigateApi = navigateApi,
            setCurrentRoute = setCurrentRoute
        )

        profile(
            resources = resources,
            navigateApi = navigateApi,
            setCurrentRoute = setCurrentRoute
        )

        settings(
            resources = resources,
            navigateApi = navigateApi,
            setCurrentRoute = setCurrentRoute
        )
    }
}