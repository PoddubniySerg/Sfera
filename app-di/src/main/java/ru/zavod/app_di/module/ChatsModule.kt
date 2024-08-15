package ru.zavod.app_di.module

import dagger.Module
import dagger.Provides
import ru.zavod.app_di.impl.ChatsDeviceRepositoryImpl
import ru.zavod.app_di.impl.ChatsRemoteRepositoryImpl
import ru.zavod.feature_chats.di.ChatsDeviceRepository
import ru.zavod.feature_chats.di.ChatsRemoteRepository

@Module
class ChatsModule {

    @Provides
    fun providesChatsDeviceRepository(storage: ChatsDeviceRepositoryImpl): ChatsDeviceRepository {
        return storage
    }

    @Provides
    fun providesChatsRemoteRepository(api: ChatsRemoteRepositoryImpl): ChatsRemoteRepository = api
}