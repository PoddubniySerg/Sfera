package ru.zavod.app_di.impl

import ru.zavod.app_core.model.User
import ru.zavod.app_core.model.UserRole
import ru.zavod.data_storage.repository.ChatsStorage
import ru.zavod.feature_chats.di.ChatsDeviceRepository
import ru.zavod.feature_profile.di.ProfileDeviceRepository
import ru.zavod.feature_profile.model.GetUserParams
import javax.inject.Inject

class ProfileDeviceRepositoryImpl @Inject constructor(
    private val storage: ChatsStorage
) : ProfileDeviceRepository {

    override suspend fun getUser(params: GetUserParams): User? {
        return storage.getUserById(userId = params.id)
    }

    override suspend fun saveUser(user: User) {
        storage.saveUser(user = user, role = UserRole.CURRENT)
    }
}