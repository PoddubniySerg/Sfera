package ru.zavod.app_navigation.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.zavod.app_navigation.R
import ru.zavod.app_navigation.route.Destination

internal data class BottomMenu(
    val destination: Destination,
    @DrawableRes val iconId: Int,
    @StringRes val headerId: Int
)

@Composable
internal fun getMenu(): List<BottomMenu> {
    return buildList {
        add(
            BottomMenu(
                destination = Destination.PROFILE,
                iconId = R.drawable.profile_icon,
                headerId = R.string.bottom_menu_profile_header
            )
        )
        add(
            BottomMenu(
                destination = Destination.SETTINGS,
                iconId = R.drawable.settings_icon,
                headerId = R.string.bottom_menu_settings_header
            )
        )
    }
}

internal fun getChats(@DrawableRes iconId: Int): BottomMenu = BottomMenu(
    destination = Destination.CHATS,
    iconId = iconId,
    headerId = R.string.bottom_menu_chats_header
)

@Composable
internal fun menuViewed(controller: NavHostController): Boolean {
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination?.hierarchy
    return destination != null
            && !destination.any { it.route == Destination.ONBOARDING.destination } == true
            && !destination.any { it.route == Destination.AUTH.destination } == true
}

@Composable
internal fun getTitle(controller: NavHostController): String? {
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination?.hierarchy
    if (destination?.any { it.route == Destination.CHATS.destination } == true) {
        return stringResource(id = R.string.bottom_menu_chats_header)
    }
    getMenu().forEach { menuItem ->
        if (destination?.any { it.route == menuItem.destination.destination } == true) {
            return stringResource(id = menuItem.headerId)
        }
    }
    return null
}