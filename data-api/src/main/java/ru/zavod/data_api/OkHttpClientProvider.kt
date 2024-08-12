package ru.zavod.data_api

import io.tasknet.data_api_tasknet_io.di.TokenProviderApi
import okhttp3.OkHttpClient
import javax.inject.Inject

class OkHttpClientProvider @Inject constructor(tokenProvider: TokenProviderApi) {

    val okHttpClient: OkHttpClient

    init {
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenProvider = tokenProvider))
            .authenticator(AppAuthenticator(tokenProvider = tokenProvider))
            .build()

    }
}