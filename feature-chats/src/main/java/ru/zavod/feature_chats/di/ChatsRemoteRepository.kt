package ru.zavod.feature_chats.di

import ru.zavod.app_core.model.User
import ru.zavod.feature_chats.model.GetUserParams

interface ChatsRemoteRepository {
    suspend fun getUser(params: GetUserParams): User?
}