package ru.zavod.data_api.model

data class UpdateMeParams(
    val username: String,
    val name: String?,
    val birthday: String?,
    val city: String?,
    val status: String?,
    val avatar: Avatar?
) {
    data class Avatar(val filename: String, val base64: String)
}
