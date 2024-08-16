package ru.zavod.feature_profile.di

import ru.zavod.app_core.model.User
import ru.zavod.feature_profile.model.GetUserParams
import ru.zavod.feature_profile.model.UpdateUserParams

interface ProfileRemoteRepository {
    suspend fun getUser(params: GetUserParams): User?
    suspend fun updateUser(params: UpdateUserParams)
}