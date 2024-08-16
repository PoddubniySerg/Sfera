package ru.zavod.app_di.module

import dagger.Module
import dagger.Provides
import ru.zavod.app_di.impl.ProfileDeviceRepositoryImpl
import ru.zavod.app_di.impl.ProfileRemoteRepositoryImpl
import ru.zavod.feature_profile.di.ProfileDeviceRepository
import ru.zavod.feature_profile.di.ProfileRemoteRepository

@Module
class ProfileModule {

    @Provides
    fun providesDeviceProfileRepository(
        storage: ProfileDeviceRepositoryImpl
    ): ProfileDeviceRepository {
        return storage
    }

    @Provides
    fun providesRemoteProfileRepository(
        api: ProfileRemoteRepositoryImpl
    ): ProfileRemoteRepository {
        return api
    }
}