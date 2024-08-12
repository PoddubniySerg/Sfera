package ru.zavod.data_api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class UpdateMeResultDto(
    @Json(name = "avatars") val avatar: Avatar,
    @Json(name = "detail") override val errors: List<ErrorDto>?
) : ThrowableResponse {

    @JsonClass(generateAdapter = true)
    internal data class Avatar(
        @Json(name = "avatar") val avatar: String?,
        @Json(name = "bigAvatar") val bigAvatar: String?,
        @Json(name = "miniAvatar") val miniAvatar: String?
    )
}