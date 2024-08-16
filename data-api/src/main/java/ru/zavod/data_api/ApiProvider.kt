package ru.zavod.data_api

import io.tasknet.data_api_tasknet_io.di.TokenProviderApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class ApiProvider @Inject constructor(
    okHttpClientProvider: OkHttpClientProvider,
    tokenProvider: TokenProviderApi
) {

    companion object {
        private const val BASE_URL = "https://plannerok.ru/api/v1/"
    }

    private val okHttpClient = okHttpClientProvider.getOkHttpClient(
        apiProvider = this,
        tokenProvider = tokenProvider
    )

    internal val api: Api

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(Api::class.java)
    }
}