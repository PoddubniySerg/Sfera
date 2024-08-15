package ru.zavod.feature_auth.usecase

import ru.zavod.feature_auth.di.DeviceAuthRepository
import ru.zavod.feature_auth.di.RemoteAuthRepository
import ru.zavod.feature_auth.model.CheckAuthCodeParams
import javax.inject.Inject

class CheckAuthCodeUseCase @Inject constructor(
    private val remoteAuthRepository: RemoteAuthRepository,
    private val deviceAuthRepository: DeviceAuthRepository
) {

    suspend fun execute(params: CheckAuthCodeParams): Boolean {
        val token = remoteAuthRepository.checkAuthCode(params = params)
        if (token.userExist) {
            deviceAuthRepository.saveToken(token = token)
        }
        return token.userExist
    }
}