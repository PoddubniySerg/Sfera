package ru.zavod.app_di.extension

import ru.zavod.data_api.model.GetUserParams
import ru.zavod.data_api.model.UpdateMeParams

internal fun ru.zavod.feature_profile.model.GetUserParams.toDataApi() = GetUserParams(id = id)

internal fun ru.zavod.feature_profile.model.UpdateUserParams.toDataApi() = UpdateMeParams(
    username = username,
    name = name,
    birthday = birthday,
    city = city,
    status = status,
    avatar = avatar?.let { UpdateMeParams.Avatar(filename = it.filename, base64 = it.base64) }
)