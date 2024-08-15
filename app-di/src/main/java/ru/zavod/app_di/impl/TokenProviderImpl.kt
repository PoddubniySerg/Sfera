package ru.zavod.app_di.impl

import io.tasknet.data_api_tasknet_io.di.TokenProviderApi
import kotlinx.coroutines.flow.StateFlow
import ru.zavod.app_core.model.Token
import ru.zavod.app_navigation.di.TokenProvider
import ru.zavod.data_storage.TokensSharedPreferences
import javax.inject.Inject

class TokenProviderImpl @Inject constructor(
    private val storage: TokensSharedPreferences
) : TokenProviderApi, TokenProvider {

    override val token: StateFlow<Token?> = storage.tokenStateFlow

    override fun getToken(): Token? = storage.getToken()

    override fun removeToken() {
        storage.removeToken()
    }
}