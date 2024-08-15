package ru.zavod.app_navigation.di

import androidx.compose.runtime.Composable

interface OnboardingParams {
    val animation: LottieAnimation?
    val content: (@Composable () -> Unit)?
}