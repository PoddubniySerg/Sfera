package ru.zavod.data_storage.model

import ru.zavod.data_storage.di.Token

data class TokenData(
    override val access: String,
    override val refresh: String,
    override val userExist: Boolean
) : Token
