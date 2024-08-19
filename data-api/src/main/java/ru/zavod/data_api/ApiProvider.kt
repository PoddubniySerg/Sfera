package ru.zavod.data_api

import io.tasknet.data_api_tasknet_io.di.TokenProviderApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class ApiProvider @Inject constructor(
    okHttpClientProvider: OkHttpClientProvider,
    tokenProvider: TokenProviderApi
) {

    private val okHttpClient = okHttpClientProvider.getOkHttpClient(
        apiProvider = this,
        tokenProvider = tokenProvider
    )

    internal val api: Api

    init {
        api = Retrofit.Builder()
            .baseUrl(Api.BASE_URL_V1)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(Api::class.java)
    }
}