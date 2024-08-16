package ru.zavod.data_api.api

import retrofit2.Response
import ru.zavod.data_api.ApiProvider
import ru.zavod.data_api.UNAUTHORIZED_CODE
import ru.zavod.data_api.dto.ThrowableResponse
import ru.zavod.data_api.exception.BadResponseException
import ru.zavod.data_api.exception.UnauthorizedException
import ru.zavod.data_api.model.GetMeResult
import ru.zavod.data_api.model.GetUserParams
import ru.zavod.data_api.utils.checkResponse
import ru.zavod.data_api.utils.toModel
import javax.inject.Inject

class ChatsApi @Inject constructor(apiProvider: ApiProvider) {

    private val api = apiProvider.api

    suspend fun getUser(params: GetUserParams): GetMeResult? {
        val response = api.getMe()
        checkResponse(response = response)
        return response.body()?.toModel()
    }
}