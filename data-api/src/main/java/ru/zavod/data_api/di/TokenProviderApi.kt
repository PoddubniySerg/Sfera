package io.tasknet.data_api_tasknet_io.di

import ru.zavod.app_core.model.Token

interface TokenProviderApi {
    fun getToken(): Token?
    fun removeToken()
}