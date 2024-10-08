package ru.zavod.data_api.utils

import ru.zavod.app_core.model.Token
import ru.zavod.data_api.Api
import ru.zavod.data_api.dto.CheckAuthCodeDto
import ru.zavod.data_api.dto.CheckAuthCodeResultDto
import ru.zavod.data_api.dto.GetMeResultDto
import ru.zavod.data_api.dto.RefreshTokenDto
import ru.zavod.data_api.dto.RefreshTokenResultDto
import ru.zavod.data_api.dto.RegisterDto
import ru.zavod.data_api.dto.RegisterResultDto
import ru.zavod.data_api.dto.SendAuthCodeDto
import ru.zavod.data_api.dto.UpdateMeDto
import ru.zavod.data_api.model.CheckAuthCodeParams
import ru.zavod.data_api.model.GetMeResult
import ru.zavod.data_api.model.RefreshTokenParams
import ru.zavod.data_api.model.RefreshTokenResult
import ru.zavod.data_api.model.RegisterParams
import ru.zavod.data_api.model.RegisterResult
import ru.zavod.data_api.model.SendAuthCodeParams
import ru.zavod.data_api.model.UpdateMeParams

internal fun SendAuthCodeParams.toDto() = SendAuthCodeDto(phone = phone)

internal fun CheckAuthCodeResultDto.toModel(): Token {
    return Token(
        access = access,
        refresh = refresh,
        userExist = userExist ?: false
    )
}

internal fun CheckAuthCodeParams.toDto() = CheckAuthCodeDto(phone = phone, code = code)

internal fun RegisterParams.toDto() = RegisterDto(phone = phone, name = name, username = username)

internal fun RegisterResultDto.toModel(): RegisterResult? {
    return RegisterResult(
        access = access ?: return null,
        refresh = refresh ?: return null,
        userId = userId ?: return null
    )
}

internal fun GetMeResultDto.toModel(): GetMeResult? {
    return GetMeResult(
        id = profile.id ?: return null,
        username = profile.username,
        name = profile.name,
        birthday = profile.birthday,
        city = profile.city,
        status = profile.status,
        avatar = "${Api.BASE_URL}${profile.avatars?.miniAvatar}",
        phone = profile.phone
    )
}

internal fun UpdateMeParams.toDto() = UpdateMeDto(
    username = username,
    name = name,
    birthday = birthday,
    city = city,
    status = status,
    avatar = avatar?.let { UpdateMeDto.Avatar(filename = it.filename, file = it.base64) }
)

internal fun RefreshTokenParams.toDto() = RefreshTokenDto(refreshToken = refresh)

internal fun RefreshTokenResultDto.toModel(): RefreshTokenResult? {
    return RefreshTokenResult(
        access = access ?: return null,
        refresh = refresh ?: return null,
        userId = userId ?: return null
    )
}