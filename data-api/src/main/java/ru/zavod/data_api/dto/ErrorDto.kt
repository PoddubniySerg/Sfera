package ru.zavod.data_api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ErrorDto(
    @Json(name = "loc") val loc: List<String>?,
    @Json(name = "msg") val msg: String?,
    @Json(name = "type") val type: String?
)