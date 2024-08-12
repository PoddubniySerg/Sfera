package ru.zavod.data_api.utils

import ru.zavod.data_api.dto.CheckAuthCodeDto
import ru.zavod.data_api.dto.CheckAuthCodeResultDto
import ru.zavod.data_api.dto.GetMeResultDto
import ru.zavod.data_api.dto.RefreshTokenDto
import ru.zavod.data_api.dto.RefreshTokenResultDto
import ru.zavod.data_api.dto.RegisterDto
import ru.zavod.data_api.dto.RegisterResultDto
import ru.zavod.data_api.dto.SendAuthCodeDto
import ru.zavod.data_api.dto.UpdateMeDto
import ru.zavod.data_api.dto.UpdateMeResultDto
import ru.zavod.data_api.model.CheckAuthCodeParams
import ru.zavod.data_api.model.GetMeResult
import ru.zavod.data_api.model.RefreshTokenParams
import ru.zavod.data_api.model.RefreshTokenResult
import ru.zavod.data_api.model.RegisterParams
import ru.zavod.data_api.model.RegisterResult
import ru.zavod.data_api.model.SendAuthCodeParams
import ru.zavod.data_api.model.Token
import ru.zavod.data_api.model.UpdateMeParams
import ru.zavod.data_api.model.UpdateMeResult

internal fun SendAuthCodeParams.toDto() = SendAuthCodeDto(phone = phone)

internal fun CheckAuthCodeResultDto.toModel(): Token? {
    return Token(
        access = access ?: return null,
        refresh = refresh ?: return null,
        userExist = userExist ?: return null
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
    val avatars = GetMeResult.Avatar(
        avatar = profile.avatars?.avatar,
        bigAvatar = profile.avatars?.bigAvatar,
        miniAvatar = profile.avatars?.miniAvatar
    )
    return GetMeResult(
        id = profile.id ?: return null,
        name = profile.name,
        username = profile.username,
        birthday = profile.birthday,
        city = profile.city,
        vk = profile.vk,
        instagram = profile.instagram,
        status = profile.status,
        avatar = profile.avatar,
        last = profile.last,
        online = profile.online,
        created = profile.created,
        phone = profile.phone,
        completedTask = profile.completedTask,
        avatars = avatars
    )
}

internal fun UpdateMeParams.toDto() = UpdateMeDto(
    name = name,
    username = username,
    birthday = birthday,
    city = city,
    vk = vk,
    instagram = instagram,
    status = status,
    avatar = UpdateMeDto.Avatar(
        filename = avatar?.filename,
        base64 = avatar?.base64
    )
)

internal fun UpdateMeResultDto.toModel(): UpdateMeResult? {
    val avatars = UpdateMeResult.Avatars(
        avatar = avatar.avatar,
        bigAvatar = avatar.bigAvatar,
        miniAvatar = avatar.miniAvatar
    )
    return UpdateMeResult(avatars = avatars)
}

internal fun RefreshTokenParams.toDto() = RefreshTokenDto(refreshToken = refresh)

internal fun RefreshTokenResultDto.toModel(): RefreshTokenResult? {
    return RefreshTokenResult(
        access = access ?: return null,
        refresh = refresh ?: return null,
        userId = userId ?: return null
    )
}