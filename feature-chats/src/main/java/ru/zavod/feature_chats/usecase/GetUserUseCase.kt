package ru.zavod.feature_chats.usecase

import ru.zavod.app_core.model.User
import ru.zavod.feature_chats.di.ChatsDeviceRepository
import ru.zavod.feature_chats.di.ChatsRemoteRepository
import ru.zavod.feature_chats.model.GetUserParams
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val remoteRepository: ChatsRemoteRepository,
    private val deviceRepository: ChatsDeviceRepository
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