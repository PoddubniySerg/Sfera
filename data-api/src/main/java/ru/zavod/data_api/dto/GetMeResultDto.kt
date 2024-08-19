package ru.zavod.data_api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class GetMeResultDto(
    @Json(name = "profile_data") val profile: Profile,
    @Json(name = "detail") override val errors: List<ErrorDto>?
) : ThrowableResponse {
    @JsonClass(generateAdapter = true)
    data class Profile(
        @Json(name = "id") val id: Long?,
        @Json(name = "username") val username: String,
        @Json(name = "name") val name: String?,
        @Json(name = "birthday") val birthday: String?,
        @Json(name = "city") val city: String?,
        @Json(name = "status") val status: String?,
        @Json(name = "avatars") val avatars: Avatars?,
        @Json(name = "phone") val phone: String?
    )

    @JsonClass(generateAdapter = true)
    data class Avatars(
        @Json(name = "miniAvatar") val miniAvatar: String?
    )
}