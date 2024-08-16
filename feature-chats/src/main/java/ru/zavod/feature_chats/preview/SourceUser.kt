package ru.zavod.feature_chats.preview

import ru.zavod.app_core.model.User

internal object SourceUser {

    fun getUserDetails(): User {
        return User(
            id = "id_preview",
            username = "username",
            name = "Семен",
            avatar = null,
            birthday = "23-02-1988",
            city = "Best City",
            status = null,
            phone = "+79250946152"
        )
    }
}