package ru.zavod.app_di.impl

import androidx.compose.runtime.Composable
import ru.zavod.app_navigation.di.NavigateApi
import ru.zavod.app_navigation.di.OnboardingParams
import ru.zavod.feature_auth.navigation.AuthNavHost
import javax.inject.Inject

class NavigateApiImpl @Inject constructor() : NavigateApi {

    @Composable
    override fun Onboarding(params: OnboardingParams, start: () -> Unit) {
        AuthNavHost()
    }

    @Composable
    override fun Auth() {
        AuthNavHost()
    }

    @Composable
    override fun Chats() {
        AuthNavHost()
    }

    @Composable
    override fun Profile() {
        AuthNavHost()
    }

    @Composable
    override fun Settings() {
        AuthNavHost()
    }
}