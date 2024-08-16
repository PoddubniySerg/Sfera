package ru.zavod.feature_profile.usecase

import ru.zavod.app_core.model.User
import ru.zavod.feature_profile.di.ProfileDeviceRepository
import ru.zavod.feature_profile.di.ProfileRemoteRepository
import ru.zavod.feature_profile.model.GetUserParams
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val remoteRepository: ProfileRemoteRepository,
    private val deviceRepository: ProfileDeviceRepository
) {

    suspend fun execute(params: GetUserParams): User {
        deviceRepository.getUser(params = params)?.let { return it }
        val user = remoteRepository.getUser(params = params)
        if (user != null) {
            deviceRepository.saveUser(user = user)
        }
        return user!!
    }
}