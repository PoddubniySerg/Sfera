package ru.zavod.data_storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.zavod.data_storage.entity.UserEntity
import ru.zavod.data_storage.entity.UserRoleEntity

@Dao
interface ChatsDao {

    @Transaction
    suspend fun saveUser(user: UserEntity, role: UserRoleEntity) {
        saveUser(userEntity = user)
        saveRole(roleEntity = role)
    }

    @Transaction
    suspend fun getUserByRole(role: String): UserEntity? {
        val id = getUserIdByRole(role = role) ?: return null
        return getUserById(userId = id)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRole(roleEntity: UserRoleEntity)

    @Query("SELECT user_id FROM users_roles WHERE role = :role")
    suspend fun getUserIdByRole(role: String): String?

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): UserEntity?

    @Transaction
    suspend fun clear() {
        clearUsers()
        clearRoles()
    }

    @Query("DELETE FROM users")
    suspend fun clearUsers()

    @Query("DELETE FROM users_roles")
    suspend fun clearRoles()
}