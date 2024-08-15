package ru.zavod.app_config.utils

import androidx.compose.runtime.Composable
import ru.zavod.app_config.model.LottieAnimationParams
import ru.zavod.app_navigation.di.LottieAnimation
import ru.zavod.app_navigation.di.OnboardingParams

internal fun LottieAnimationParams.toNavigation(): LottieAnimation{
    val params = this
    return object : LottieAnimation{
        override val animationId: Int = params.animationId
        override val millis: Long = params.millis
    }
}

internal fun ru.zavod.app_config.model.OnboardingParams.toNavigation(): OnboardingParams{
    val params = this
    return object : OnboardingParams{
        override val animation: LottieAnimation? = params.animation?.toNavigation()
        override val content: @Composable() (() -> Unit)? = params.content
    }
}