package ru.zavod.feature_auth.model

data class RegisterResult(val access: String, val refresh: String, val userId: Long)
