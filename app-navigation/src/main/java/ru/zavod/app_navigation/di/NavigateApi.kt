package ru.zavod.app_navigation.di

import androidx.compose.runtime.Composable

interface NavigateApi {

    @Composable
    fun ToOnboarding(params: OnboardingParams, start: () -> Unit)

    @Composable
    fun ToAuth()

    @Composable
    fun ToChats()

    @Composable
    fun ToProfile()

    @Composable
    fun ToSettings()
}