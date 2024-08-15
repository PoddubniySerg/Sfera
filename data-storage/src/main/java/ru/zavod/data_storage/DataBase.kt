package ru.zavod.data_storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.zavod.data_storage.dao.ChatsDao
import ru.zavod.data_storage.entity.UserEntity
import ru.zavod.data_storage.entity.UserRoleEntity

@Database(
    version = 1,
    entities = [
        UserEntity::class,
        UserRoleEntity::class
    ],
//    autoMigrations = [
//        AutoMigration(from = 1, to = 2)
//    ]
)
abstract class DataBase : RoomDatabase() {
    abstract fun chatsDao(): ChatsDao
}