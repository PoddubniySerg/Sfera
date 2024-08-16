package ru.zavod.data_api.model

data class GetMeResult(
    val id: Long,
    val username: String,
    val name: String?,
    val birthday: String?,
    val city: String?,
    val status: String?,
    val avatar: String?,
    val phone: String?
)
