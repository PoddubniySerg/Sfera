package ru.zavod.app_di.impl

import ru.zavod.app_core.model.Token
import ru.zavod.data_storage.TokensSharedPreferences
import ru.zavod.feature_auth.di.DeviceAuthRepository
import javax.inject.Inject

class AuthDeviceRepositoryImpl @Inject constructor(
    private val storage: TokensSharedPreferences
) : DeviceAuthRepository {

    override suspend fun saveToken(token: Token) {
        storage.saveToken(token = token)
    }
}