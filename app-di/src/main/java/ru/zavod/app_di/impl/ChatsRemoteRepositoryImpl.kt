package ru.zavod.app_di.impl

import ru.zavod.app_core.model.User
import ru.zavod.app_di.extension.toChatsModel
import ru.zavod.app_di.extension.toDataApi
import ru.zavod.data_api.api.ChatsApi
import ru.zavod.feature_chats.di.ChatsRemoteRepository
import ru.zavod.feature_chats.model.GetUserParams
import javax.inject.Inject

class ChatsRemoteRepositoryImpl @Inject constructor(
    private val api: ChatsApi
) : ChatsRemoteRepository {

    override suspend fun getUser(params: GetUserParams): User? {
        return api.getUser(params = params.toDataApi())?.toChatsModel()
    }
}