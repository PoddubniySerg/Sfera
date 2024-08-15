package ru.zavod.feature_auth.usecase

import ru.zavod.app_core.model.Token
import ru.zavod.feature_auth.di.DeviceAuthRepository
import ru.zavod.feature_auth.di.RemoteAuthRepository
import ru.zavod.feature_auth.model.RegisterParams
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val remoteAuthRepository: RemoteAuthRepository,
    private val deviceAuthRepository: DeviceAuthRepository
) {

    suspend fun execute(params: RegisterParams) {
        val result = remoteAuthRepository.register(params = params)!!
        val token = Token(
            access = result.access,
            refresh = result.refresh,
            userExist = result.userId > 0
        )
        deviceAuthRepository.saveToken(token = token)
    }
}