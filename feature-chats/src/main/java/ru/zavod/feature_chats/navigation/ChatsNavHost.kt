package ru.zavod.feature_chats.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.zavod.feature_chats.R

@Composable
fun ChatsNavHost() {
    val navController = rememberNavController()
    val resources = LocalContext.current.resources
    NavHost(
        navController = navController,
        startDestination = stringResource(R.string.chats_destination),
        modifier = Modifier.fillMaxSize()
    ) {
        chat(resources = resources)
        chats(navController = navController, resources = resources)
    }
}