package ru.zavod.feature_auth.di

import ru.zavod.app_core.model.Token

interface DeviceAuthRepository {
    suspend fun saveToken(token: ru.zavod.app_core.model.Token)
}