package ru.zavod.app_config.utils

import androidx.compose.runtime.Composable
import ru.zavod.app_navigation.di.OnboardingParams

internal fun ru.zavod.app_config.model.OnboardingParams.toNavigation(): OnboardingParams {
    val params = this
    return object : OnboardingParams {
        override val animationId: Int? = params?.animationId
        override val millis: Long? = params.millis
        override val content: @Composable() (() -> Unit)? = params.content
    }
}