package ru.zavod.feature_chats.navigation

import android.content.res.Resources
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.zavod.feature_chats.R
import ru.zavod.feature_chats.ui.Chat

internal fun NavGraphBuilder.chat(resources: Resources) {
    composable(
        route = resources.getString(R.string.chat_destination),
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        popEnterTransition = null
    ) {
        Chat()
    }
}