package ru.zavod.feature_auth.navigation

import android.content.res.Resources
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.zavod.feature_auth.R
import ru.zavod.feature_auth.ui.register.Register

internal fun NavGraphBuilder.register(navController: NavHostController, resources: Resources) {
    val registerDestination = resources.getString(R.string.register_destination)
    val countryAliasKey = resources.getString(R.string.country_alias_key)
    val phoneNumberKey = resources.getString(R.string.phone_number_key)
    composable(
        route = "$registerDestination/{$countryAliasKey}/{$phoneNumberKey}",
        arguments = listOf(
            navArgument(countryAliasKey) { type = NavType.StringType },
            navArgument(phoneNumberKey) { type = NavType.StringType }
        ),
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        popEnterTransition = null
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.let { args ->
            val countryAlias = args.getString(countryAliasKey)
            val phoneNumber = args.getString(phoneNumberKey)
            Register(
                countryAlias = countryAlias,
                phone = phoneNumber,
                onBack = navController::popBackStack
            )
        }
    }
}