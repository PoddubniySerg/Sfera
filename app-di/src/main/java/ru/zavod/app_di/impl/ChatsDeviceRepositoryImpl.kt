package ru.zavod.app_di.impl

import ru.zavod.app_core.model.User
import ru.zavod.app_core.model.UserRole
import ru.zavod.data_storage.repository.ChatsStorage
import ru.zavod.feature_chats.di.ChatsDeviceRepository
import ru.zavod.feature_chats.model.GetUserParams
import javax.inject.Inject

class ChatsDeviceRepositoryImpl @Inject constructor(
    private val storage: ChatsStorage
) : ChatsDeviceRepository {

    override suspend fun getUser(params: GetUserParams): User? {
        return storage.getUserById(userId = params.id)
    }

    override suspend fun saveUser(user: User) {
        storage.saveUser(user = user, role = UserRole.CURRENT)
    }
}