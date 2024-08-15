package ru.zavod.data_api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class UpdateMeDto(
    @Json(name = "name") val name: String?,
    @Json(name = "birthday") val birthday: String?,
    @Json(name = "city") val city: String?,
    @Json(name = "status") val status: String?
)