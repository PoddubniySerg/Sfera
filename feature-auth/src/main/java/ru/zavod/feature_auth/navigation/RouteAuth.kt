package ru.zavod.feature_auth.navigation

import android.content.res.Resources
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.zavod.feature_auth.R
import ru.zavod.feature_auth.ui.auth.Auth

internal fun NavGraphBuilder.auth(
    navController: NavHostController,
    resources: Resources,
    success: () -> Unit
) {
    composable(route = resources.getString(R.string.auth_destination)) {
        val registerDestination = stringResource(id = R.string.register_destination)
        Auth(
            register = { countryAlias, phoneNumber ->
                val registereRoute = "$registerDestination/$countryAlias/$phoneNumber"
                navController.navigate(route = registereRoute)
            },
            success = success
        )
    }
}