package ru.zavod.feature_profile.usecase

import ru.zavod.app_core.model.User
import ru.zavod.feature_profile.di.ProfileDeviceRepository
import ru.zavod.feature_profile.di.ProfileRemoteRepository
import ru.zavod.feature_profile.model.GetUserParams
import ru.zavod.feature_profile.model.UpdateUserParams
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val remoteRepository: ProfileRemoteRepository,
    private val deviceRepository: ProfileDeviceRepository
) {

    suspend fun execute(params: UpdateUserParams?): User {
        remoteRepository.updateUser(params = params!!)
        val getParams = GetUserParams(id = params.id)
        val user = remoteRepository.getUser(params = getParams)
        if (user != null) {
            deviceRepository.saveUser(user = user)
        }
        return user!!
    }
}