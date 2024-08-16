package ru.zavod.feature_profile.utils

import ru.zavod.app_core.model.User
import ru.zavod.feature_profile.model.UpdateUserParams

internal fun User.toUpdateParams(avatar: UpdateUserParams.Avatar?): UpdateUserParams =
    UpdateUserParams(
        id = id,
        username = username,
        name = name,
        birthday = birthday,
        city = city,
        status = status,
        avatar = avatar
    )