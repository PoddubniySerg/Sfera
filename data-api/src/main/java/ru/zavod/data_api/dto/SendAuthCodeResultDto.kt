package ru.zavod.data_api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class SendAuthCodeResultDto(
    @Json(name = "is_success") val success: Boolean?,
    @Json(name = "detail") override val errors: List<ErrorDto>?
): ThrowableResponse