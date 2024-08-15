package ru.zavod.app_di.module

import dagger.Module
import dagger.Provides
import io.tasknet.data_api_tasknet_io.di.TokenProviderApi
import ru.zavod.app_di.impl.TokenProviderImpl

@Module
class DataApiModule {

    @Provides
    fun providesTokenProvider(storage: TokenProviderImpl): TokenProviderApi {
        return storage
    }
}