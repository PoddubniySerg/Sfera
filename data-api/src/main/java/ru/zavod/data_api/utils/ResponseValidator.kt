package ru.zavod.data_api.utils

import retrofit2.Response
import ru.zavod.data_api.UNAUTHORIZED_CODE
import ru.zavod.data_api.dto.ThrowableResponse
import ru.zavod.data_api.exception.BadResponseException
import ru.zavod.data_api.exception.UnauthorizedException

internal fun <T : ThrowableResponse> checkResponse(response: Response<T?>) {
    when {
        response?.code() == UNAUTHORIZED_CODE -> throw UnauthorizedException()
        response?.isSuccessful == false -> throw BadResponseException(
            message = response.body()?.errors?.firstOrNull()?.msg
        )
    }
}