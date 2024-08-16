package ru.zavod.feature_profile.model

data class UpdateUserParams(
    val id: String,
    val username: String,
    val name: String?,
    val birthday: String?,
    val city: String?,
    val status: String?,
    val avatar: Avatar?
){
    data class Avatar(val filename: String, val base64: String)
}
