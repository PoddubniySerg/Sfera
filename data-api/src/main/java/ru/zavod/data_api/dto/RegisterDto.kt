package ru.zavod.data_api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class RegisterDto(
    @Json(name = "phone") val phone: String,
    @Json(name = "name") val name: String,
    @Json(name = "username") val username: String
)