package ru.zavod.feature_auth.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.zavod.feature_auth.R

@Composable
fun AuthNavHost() {
    val navController = rememberNavController()
    val resources = LocalContext.current.resources
    NavHost(
        navController = navController,
        startDestination = stringResource(R.string.auth_destination),
        modifier = Modifier.fillMaxSize()
    ) {
        auth(navController = navController, resources = resources)
        register(navController = navController, resources = resources)
    }
}