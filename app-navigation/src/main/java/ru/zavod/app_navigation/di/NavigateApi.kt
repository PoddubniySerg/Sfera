package ru.zavod.app_navigation.di

import androidx.compose.runtime.Composable
import ru.zavod.app_navigation.model.AuthParams

interface NavigateApi {

    @Composable
    fun ToOnboarding(params: OnboardingParams, start: () -> Unit)

    @Composable
    fun ToAuth(params: AuthParams)

    @Composable
    fun ToChats()

    @Composable
    fun ToProfile()

    @Composable
    fun ToSettings()
}