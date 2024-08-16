package ru.zavod.data_api.api

import retrofit2.Response
import ru.zavod.app_core.model.Token
import ru.zavod.data_api.ApiProvider
import ru.zavod.data_api.UNAUTHORIZED_CODE
import ru.zavod.data_api.dto.ThrowableResponse
import ru.zavod.data_api.exception.BadResponseException
import ru.zavod.data_api.exception.UnauthorizedException
import ru.zavod.data_api.model.CheckAuthCodeParams
import ru.zavod.data_api.model.GetMeResult
import ru.zavod.data_api.model.RefreshTokenParams
import ru.zavod.data_api.model.RefreshTokenResult
import ru.zavod.data_api.model.RegisterParams
import ru.zavod.data_api.model.RegisterResult
import ru.zavod.data_api.model.SendAuthCodeParams
import ru.zavod.data_api.utils.checkResponse
import ru.zavod.data_api.utils.toDto
import ru.zavod.data_api.utils.toModel
import javax.inject.Inject

class AuthApi @Inject constructor(apiProvider: ApiProvider) {

    private val api = apiProvider.api

    suspend fun sendAuthCode(params: SendAuthCodeParams): Boolean? {
        val response = api.sendAuthCode(sendAuthCodeDto = params.toDto())
        checkResponse(response = response)
        return response.body()?.success
    }

    suspend fun checkAuthCode(params: CheckAuthCodeParams): Token {
        val response = api.checkAuthCode(checkAuthCodeDto = params.toDto())
        checkResponse(response = response)
        return response.body()!!.toModel()
    }

    suspend fun register(params: RegisterParams): RegisterResult? {
        val response = api.register(registerDto = params.toDto())
        checkResponse(response = response)
        return response.body()?.toModel()
    }

    suspend fun getMe(): GetMeResult? {
        val response = api.getMe()
        checkResponse(response = response)
        return response.body()?.toModel()
    }
}