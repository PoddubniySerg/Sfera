package ru.zavod.app_config.model

import androidx.compose.runtime.Composable
import ru.zavod.app_navigation.di.OnboardingParams

data class OnboardingParams(
    val animation: LottieAnimationParams? = null,
    val content: @Composable() (() -> Unit)? = null
)
