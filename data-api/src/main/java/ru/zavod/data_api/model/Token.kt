package ru.zavod.data_api.model

data class Token(val access: String, val refresh: String, val userExist: Boolean)
