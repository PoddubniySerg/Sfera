package ru.zavod.app_config.model

import androidx.annotation.RawRes
import ru.zavod.app_navigation.di.LottieAnimation

data class LottieAnimationParams(
    @RawRes val animationId: Int,
    val millis: Long
)
