package ru.zavod.data_storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class UserEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "avatar") val avatar: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "city") val city: String?,
    @ColumnInfo(name = "birthday") val birthday: String?,
    @ColumnInfo(name = "status") val status: String?
)