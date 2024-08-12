package ru.zavod.data_api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class CheckAuthCodeResultDto(
    @Json(name = "access_token") val access: String?,
    @Json(name = "refresh_token") val refresh: String?,
    @Json(name = "user_id") val userId: Long?,
    @Json(name = "is_user_exists") val userExist: Boolean?,
    @Json(name = "detail") override val errors: List<ErrorDto>?
): ThrowableResponse