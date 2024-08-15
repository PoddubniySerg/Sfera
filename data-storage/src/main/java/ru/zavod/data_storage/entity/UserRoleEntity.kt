package ru.zavod.data_storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "users_roles", primaryKeys = ["user_id", "role"])
class UserRoleEntity(
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "role") val role: String
)