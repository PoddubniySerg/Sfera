package ru.zavod.app_navigation.di

import androidx.annotation.RawRes

interface LottieAnimation {
    @get:RawRes
    val animationId: Int
    val millis: Long
}