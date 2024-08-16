package ru.zavod.data_api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class UpdateMeDto(
    @Json(name = "username") val username: String,
    @Json(name = "name") val name: String?,
    @Json(name = "birthday") val birthday: String?,
    @Json(name = "city") val city: String?,
    @Json(name = "status") val status: String?,
    @Json(name = "avatar") val avatar: Avatar?
) {
    @JsonClass(generateAdapter = true)
    data class Avatar(
        @Json(name = "filename") val filename: String,
        @Json(name = "base_64") val file: String
    )
}