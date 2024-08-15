package ru.zavod.app_core.model

data class Token(val access: String?, val refresh: String?, val userExist: Boolean)
