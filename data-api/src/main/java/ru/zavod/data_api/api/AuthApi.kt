package ru.zavod.data_api.api

import retrofit2.Response
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
import ru.zavod.data_api.model.Token
import ru.zavod.data_api.model.UpdateMeParams
import ru.zavod.data_api.model.UpdateMeResult
import ru.zavod.data_api.utils.toDto
import ru.zavod.data_api.utils.toModel
import javax.inject.Inject

class AuthApi @Inject constructor(apiProvider: ApiProvider) {

    private val apiV2 = apiProvider.api

    suspend fun sendAuthCode(params: SendAuthCodeParams): Boolean? {
        val response = apiV2.sendAuthCode(sendAuthCodeDto = params.toDto())
        checkResponse(response = response)
        return response.body()?.success
    }

    suspend fun checkAuthCode(params: CheckAuthCodeParams): Token? {
        val response = apiV2.checkAuthCode(checkAuthCodeDto = params.toDto())
        checkResponse(response = response)
        return response.body()?.toModel()
    }

    suspend fun register(params: RegisterParams): RegisterResult? {
        val response = apiV2.register(registerDto = params.toDto())
        checkResponse(response = response)
        return response.body()?.toModel()
    }

    suspend fun getMe(): GetMeResult? {
        val response = apiV2.getMe()
        checkResponse(response = response)
        return response.body()?.toModel()
    }

    suspend fun updateMe(params: UpdateMeParams): UpdateMeResult? {
        val response = apiV2.updateMe(updateMeDto = params.toDto())
        checkResponse(response = response)
        return response.body()?.toModel()
    }

    suspend fun refreshToken(params: RefreshTokenParams): RefreshTokenResult? {
        val response = apiV2.refreshToken(refreshTokenDto = params.toDto())
        checkResponse(response = response)
        return response.body()?.toModel()
    }

    suspend fun cjeckJwt(): String? {
        val response = apiV2.checkJwt()
        when {
            response.code() == UNAUTHORIZED_CODE -> throw UnauthorizedException()
            !response.isSuccessful -> throw BadResponseException(message = response.body())
        }
        return response.body()
    }

    private fun <T : ThrowableResponse> checkResponse(response: Response<T>) {
        when {
            response.code() == UNAUTHORIZED_CODE -> throw UnauthorizedException()
            !response.isSuccessful -> throw BadResponseException(
                message = response.body()?.errors?.firstOrNull()?.msg
            )
        }
    }
}