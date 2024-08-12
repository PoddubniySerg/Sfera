package io.tasknet.data_api_tasknet_io.di

import ru.zavod.data_api.model.Token

interface TokenProviderApi {
    fun getToken(): Token?
    fun removeToken()
}