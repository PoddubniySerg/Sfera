package ru.zavod.app_di.impl

import androidx.compose.runtime.Composable
import ru.zavod.app_navigation.di.NavigateApi
import ru.zavod.app_navigation.di.OnboardingParams
import ru.zavod.feature_auth.navigation.AuthNavHost
import ru.zavod.feature_onboarding.ui.Onboarding
import javax.inject.Inject

class NavigateApiImpl @Inject constructor() : NavigateApi {

    @Composable
    override fun ToOnboarding(params: OnboardingParams, start: () -> Unit) {

        when (val delay = params.millis) {

            null -> Onboarding(
                animationId = params.animationId,
                content = params.content,
                start = start
            )

            else -> Onboarding(
                timeMillis = delay,
                animationId = params.animationId,
                content = params.content,
                start = start
            )
        }
    }

    @Composable
    override fun ToAuth() {
        AuthNavHost()
    }

    @Composable
    override fun ToChats() {
        Onboarding(
            animationId = null,
            content = null,
            start = {}
        )
    }

    @Composable
    override fun ToProfile() {
        AuthNavHost()
    }

    @Composable
    override fun ToSettings() {
        AuthNavHost()
    }
}