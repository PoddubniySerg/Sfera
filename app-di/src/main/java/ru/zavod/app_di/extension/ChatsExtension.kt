package ru.zavod.app_di.extension

import ru.zavod.app_core.model.User
import ru.zavod.data_api.model.GetUserParams

internal fun ru.zavod.feature_chats.model.GetUserParams.toDataApi() = GetUserParams(id = id)

internal fun ru.zavod.data_api.model.GetMeResult.toChatsModel() = User(
    id = id.toString(),
    name = name,
    birthday = birthday,
    city = city,
    status = status,
    avatar = avatar,
    phone = phone
)