package ru.zavod.data_api

import io.tasknet.data_api_tasknet_io.di.TokenProviderApi
import okhttp3.OkHttpClient
import javax.inject.Inject

class OkHttpClientProvider @Inject constructor() {

    private var okHttpClient: OkHttpClient? = null

    internal fun getOkHttpClient(
        apiProvider: ApiProvider,
        tokenProvider: TokenProviderApi
    ): OkHttpClient {
        if (okHttpClient == null) {
            okHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(tokenProvider = tokenProvider))
                .authenticator(
                    AppAuthenticator(
                        tokenProvider = tokenProvider,
                        apiProvider = apiProvider
                    )
                )
                .build()
        }
        return okHttpClient!!
    }
}