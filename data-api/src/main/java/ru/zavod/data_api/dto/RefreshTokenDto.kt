package ru.zavod.data_api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class RefreshTokenDto(@Json(name = "refresh_token") val refreshToken: String)