package ru.zavod.data_api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class CheckAuthCodeDto(
    @Json(name = "phone") val phone: String,
    @Json(name = "code") val code: String
)