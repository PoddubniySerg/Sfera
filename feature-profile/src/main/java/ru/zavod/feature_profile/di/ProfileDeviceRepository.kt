package ru.zavod.feature_profile.di

import ru.zavod.app_core.model.User
import ru.zavod.feature_profile.model.GetUserParams

interface ProfileDeviceRepository {
    suspend fun getUser(params: GetUserParams): User?
    suspend fun saveUser(user: User)
}