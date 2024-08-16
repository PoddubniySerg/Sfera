package ru.zavod.app_core.model

data class User(
    val id: String,
    val username: String,
    val avatar: String?,
    val phone: String?,
    val name: String?,
    val city: String?,
    val birthday: String?,
    val status: String?
)
