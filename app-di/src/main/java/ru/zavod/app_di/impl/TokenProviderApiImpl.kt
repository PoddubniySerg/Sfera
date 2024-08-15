package ru.zavod.app_di.impl

import io.tasknet.data_api_tasknet_io.di.TokenProviderApi
import ru.zavod.app_core.model.Token
import ru.zavod.data_storage.TokensSharedPreferences
import javax.inject.Inject

class TokenProviderApiImpl @Inject constructor(
    private val storage: TokensSharedPreferences
) : TokenProviderApi {

    override fun getToken(): Token? = storage.getToken()

    override fun removeToken() {
        storage.removeToken()
    }
}