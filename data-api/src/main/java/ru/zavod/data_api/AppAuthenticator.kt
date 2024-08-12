package ru.zavod.data_api

import io.tasknet.data_api_tasknet_io.di.TokenProviderApi
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AppAuthenticator @Inject constructor(
    private val tokenProvider: TokenProviderApi
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        if (response.code() == UNAUTHORIZED_CODE) {
            tokenProvider.removeToken()
            return null
        }

        return response.request()
    }
}