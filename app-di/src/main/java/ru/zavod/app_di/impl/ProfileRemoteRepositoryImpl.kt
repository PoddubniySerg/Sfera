package ru.zavod.app_di.impl

import ru.zavod.app_core.model.User
import ru.zavod.app_di.extension.toChatsModel
import ru.zavod.app_di.extension.toDataApi
import ru.zavod.data_api.api.ProfileApi
import ru.zavod.feature_profile.di.ProfileRemoteRepository
import ru.zavod.feature_profile.model.GetUserParams
import ru.zavod.feature_profile.model.UpdateUserParams
import javax.inject.Inject

class ProfileRemoteRepositoryImpl @Inject constructor(
    private val api: ProfileApi
) : ProfileRemoteRepository {

    override suspend fun getUser(params: GetUserParams): User? {
        return api.getUser(params = params.toDataApi())?.toChatsModel()
    }

    override suspend fun updateUser(params: UpdateUserParams) {
        api.updateUser(params = params.toDataApi())
    }
}