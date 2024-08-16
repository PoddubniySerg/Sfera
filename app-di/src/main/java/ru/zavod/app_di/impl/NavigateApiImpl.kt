package ru.zavod.app_di.impl

import androidx.compose.runtime.Composable
import ru.zavod.app_navigation.model.AuthParams
import ru.zavod.app_navigation.di.NavigateApi
import ru.zavod.app_navigation.di.OnboardingParams
import ru.zavod.feature_auth.model.Auth
import ru.zavod.feature_auth.navigation.AuthNavHost
import ru.zavod.feature_chats.navigation.ChatsNavHost
import ru.zavod.feature_onboarding.ui.Onboarding
import ru.zavod.feature_profile.ui.Profile
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
    override fun ToAuth(params: AuthParams) {
        AuthNavHost(params = Auth(success = params.success))
    }

    @Composable
    override fun ToChats() {
        ChatsNavHost()
    }

    @Composable
    override fun ToProfile() {
        Profile()
    }

    @Composable
    override fun ToSettings() {
        Onboarding(
            animationId = null,
            content = null,
            start = {}
        )
    }
}