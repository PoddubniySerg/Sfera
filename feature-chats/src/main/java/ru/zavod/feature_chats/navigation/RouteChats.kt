package ru.zavod.feature_chats.navigation

import android.content.res.Resources
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.zavod.feature_chats.R
import ru.zavod.feature_chats.ui.Chats

internal fun NavGraphBuilder.chats(
    navController: NavHostController,
    resources: Resources
) {
    composable(route = resources.getString(R.string.chats_destination)) {
        val chatDestination = stringResource(id = R.string.chat_destination)
        Chats(onChatClick = { navController.navigate(route = "$chatDestination/${it.title}") })
    }
}