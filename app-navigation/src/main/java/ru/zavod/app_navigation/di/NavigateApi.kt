package ru.zavod.app_navigation.di

import androidx.compose.runtime.Composable

interface NavigateApi {

    @Composable
    fun Onboarding(params: OnboardingParams, start: () -> Unit)

    @Composable
    fun Auth()

    @Composable
    fun Chats()

    @Composable
    fun Profile()

    @Composable
    fun Settings()
}