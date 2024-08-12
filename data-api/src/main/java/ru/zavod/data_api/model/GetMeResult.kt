package ru.zavod.data_api.model

import ru.zavod.data_api.dto.GetMeResultDto.Avatar

data class GetMeResult(
    val id: Long,
    val name: String?,
    val username: String?,
    val birthday: String?,
    val city: String?,
    val vk: String?,
    val instagram: String?,
    val status: String?,
    val avatar: String?,
    val last: String?,
    val online: Boolean?,
    val created: String?,
    val phone: String?,
    val completedTask: Int?,
    val avatars: Avatar?
) {
    data class Avatar(
        val avatar: String?,
        val bigAvatar: String?,
        val miniAvatar: String?
    )
}
