package ru.zavod.app_di.impl

import ru.zavod.app_core.model.Token
import ru.zavod.app_di.extension.toAuthModel
import ru.zavod.app_di.extension.toDataApi
import ru.zavod.data_api.api.AuthApi
import ru.zavod.data_api.exception.UnauthorizedException
import ru.zavod.feature_auth.di.RemoteAuthRepository
import ru.zavod.feature_auth.model.CheckAuthCodeParams
import ru.zavod.feature_auth.model.RegisterParams
import ru.zavod.feature_auth.model.RegisterResult
import ru.zavod.feature_auth.model.SendAuthCodeParams
import ru.zavod.feature_auth.model.SendAuthCodeResult
import javax.inject.Inject

class AuthRemoteRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : RemoteAuthRepository {

    override suspend fun sendAuthCode(params: SendAuthCodeParams): SendAuthCodeResult? {
        return exceptionHandler {
            val success = authApi.sendAuthCode(params = params.toDataApi())
            success?.let { SendAuthCodeResult(success = it) }
        }
    }

    override suspend fun checkAuthCode(params: CheckAuthCodeParams): Token {
        return exceptionHandler {
            authApi.checkAuthCode(params = params.toDataApi())
        }
    }

    override suspend fun register(params: RegisterParams): RegisterResult? {
        return exceptionHandler {
            authApi.register(params = params.toDataApi())?.toAuthModel()
        }
    }

    private suspend fun <T> exceptionHandler(execute: suspend () -> T): T {
        return try {
            execute()
        } catch (e: UnauthorizedException) {
            throw ru.zavod.feature_auth.exception.UnauthorizedException()
        }
    }
}