package ru.zavod.data_api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class RegisterResultDto(
    @Json(name = "access_token") val access: String?,
    @Json(name = "refresh_token") val refresh: String?,
    @Json(name = "user_id") val userId: Long?,
    @Json(name = "detail") override val errors: List<ErrorDto>?
): ThrowableResponse