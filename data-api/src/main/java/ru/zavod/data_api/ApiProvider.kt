package ru.zavod.data_api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class ApiProvider @Inject constructor(okHttpClientProvider: OkHttpClientProvider) {

    companion object {
        private const val BASE_URL = "https://plannerok.ru/api/v1/"
    }

    internal val api: Api

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClientProvider.okHttpClient)
            .build()
            .create(Api::class.java)
    }
}