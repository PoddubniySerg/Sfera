package ru.zavod.data_api.model

data class RefreshTokenResult(val access: String, val refresh: String, val userId: Long)
