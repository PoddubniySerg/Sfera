package ru.zavod.app_config.model

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable

data class OnboardingParams(
    @RawRes val animationId: Int? = null,
    val millis: Long? = null,
    val content: @Composable() (() -> Unit)? = null
)
