package ru.zavod.app_di.module

import dagger.Module
import dagger.Provides
import io.tasknet.data_api_tasknet_io.di.TokenProviderApi
import ru.zavod.app_di.impl.AuthDeviceRepositoryImpl
import ru.zavod.app_di.impl.AuthRemoteRepositoryImpl
import ru.zavod.feature_auth.di.DeviceAuthRepository
import ru.zavod.feature_auth.di.RemoteAuthRepository

@Module
class AuthModule {

    @Provides
    fun providesDeviceAuthRepository(storage: AuthDeviceRepositoryImpl): DeviceAuthRepository {
        return storage
    }

    @Provides
    fun providesRemoteAuthRepository(api: AuthRemoteRepositoryImpl): RemoteAuthRepository = api
}