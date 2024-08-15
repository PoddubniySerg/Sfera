package ru.zavod.app_navigation.di

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable

interface OnboardingParams {
    @get:RawRes
    val animationId: Int?
    val millis: Long?
    val content: (@Composable () -> Unit)?
}