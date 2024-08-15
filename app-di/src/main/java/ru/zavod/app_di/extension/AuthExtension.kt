package ru.zavod.app_di.extension

import ru.zavod.data_api.model.CheckAuthCodeParams
import ru.zavod.data_api.model.RegisterParams
import ru.zavod.data_api.model.SendAuthCodeParams
import ru.zavod.feature_auth.model.RegisterResult

internal fun ru.zavod.feature_auth.model.SendAuthCodeParams.toDataApi() = SendAuthCodeParams(
    phone = phone
)

internal fun ru.zavod.feature_auth.model.CheckAuthCodeParams.toDataApi() = CheckAuthCodeParams(
    phone = phone,
    code = code
)

internal fun ru.zavod.feature_auth.model.RegisterParams.toDataApi() = RegisterParams(
    phone = phone,
    name = name,
    username = username
)

internal fun ru.zavod.data_api.model.RegisterResult.toAuthModel() = RegisterResult(
    access = access,
    refresh = refresh,
    userId = userId
)