package ru.zavod.data_api.model

import ru.zavod.data_api.dto.GetMeResultDto.Avatar

data class UpdateMeParams(
    val name: String?,
    val username: String?,
    val birthday: String?,
    val city: String?,
    val vk: String?,
    val instagram: String?,
    val status: String?,
    val avatar: Avatar?
) {
    data class Avatar(
        val filename: String?,
        val base64: String?
    )
}
