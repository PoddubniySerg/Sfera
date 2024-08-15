package ru.zavod.feature_auth.model

import ru.zavod.app_core.EMPTY_STRING

data class RegisterParams(
    val phone: String = EMPTY_STRING,
    val name: String = EMPTY_STRING,
    val username: String = EMPTY_STRING
)
