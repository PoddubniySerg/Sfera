package ru.zavod.data_api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class UpdateMeResultDto(
    @Json(name = "status") val status: String?, override val errors: List<ErrorDto>?
) : ThrowableResponse