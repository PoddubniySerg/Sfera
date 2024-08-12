package ru.zavod.data_api.model

data class UpdateMeResult(
    val avatars: Avatars?
) {
    data class Avatars(
        val avatar: String?,
        val bigAvatar: String?,
        val miniAvatar: String?
    )
}
