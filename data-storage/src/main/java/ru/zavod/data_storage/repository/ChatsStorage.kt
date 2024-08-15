package ru.zavod.data_storage.repository

import ru.zavod.app_core.model.User
import ru.zavod.app_core.model.UserRole
import ru.zavod.data_storage.DataBaseProvider
import ru.zavod.data_storage.utils.toEntity
import ru.zavod.data_storage.utils.toUser
import javax.inject.Inject

class ChatsStorage @Inject constructor(
    dataBaseProvider: DataBaseProvider
) {

    private val dao = dataBaseProvider.getDataBase().chatsDao()

    suspend fun saveUser(user: User, role: UserRole) {
        dao.saveUser(user = user.toEntity(), role = role.toEntity(userId = user.id))
    }

    suspend fun getUserById(userId: String?): User? {
        return when (val id = userId) {
            null -> dao.getUserByRole(role = UserRole.CURRENT.name)?.toUser()
            else -> dao.getUserById(userId = id)?.toUser()
        }
    }
}