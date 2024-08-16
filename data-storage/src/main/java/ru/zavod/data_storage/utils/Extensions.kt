package ru.zavod.data_storage.utils

import ru.zavod.app_core.model.User
import ru.zavod.app_core.model.UserRole
import ru.zavod.data_storage.entity.UserEntity
import ru.zavod.data_storage.entity.UserRoleEntity

internal fun User.toEntity() = UserEntity(
    id = id,
    username = username,
    name = name,
    birthday = birthday,
    city = city,
    status = status,
    avatar = avatar,
    phone = phone
)

internal fun UserRole.toEntity(userId: String) = UserRoleEntity(userId = userId, role = name)

internal fun UserEntity.toUser() = User(
    id = id,
    username = username,
    name = name,
    birthday = birthday,
    city = city,
    status = status,
    avatar = avatar,
    phone = phone
)