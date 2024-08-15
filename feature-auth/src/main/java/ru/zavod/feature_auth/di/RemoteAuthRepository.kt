package ru.zavod.feature_auth.di

import ru.zavod.app_core.model.Token
import ru.zavod.feature_auth.model.CheckAuthCodeParams
import ru.zavod.feature_auth.model.RegisterParams
import ru.zavod.feature_auth.model.RegisterResult
import ru.zavod.feature_auth.model.SendAuthCodeParams
import ru.zavod.feature_auth.model.SendAuthCodeResult

interface RemoteAuthRepository {
    suspend fun sendAuthCode(params: SendAuthCodeParams): SendAuthCodeResult?
    suspend fun checkAuthCode(params: CheckAuthCodeParams): Token
    suspend fun register(params: RegisterParams): RegisterResult?
}