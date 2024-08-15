package ru.zavod.app_navigation.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.zavod.app_navigation.R

internal data class BottomMenu(
    @StringRes val route: Int,
    @DrawableRes val iconId: Int,
    @StringRes val headerId: Int
)

@Composable
internal fun getMenu(): List<BottomMenu> {
    return buildList {
        add(
            BottomMenu(
                route = R.string.profile_destination,
                iconId = R.drawable.profile_icon,
                headerId = R.string.bottom_menu_profile_header
            )
        )
        add(
            BottomMenu(
                route = R.string.settings_destination,
                iconId = R.drawable.settings_icon,
                headerId = R.string.bottom_menu_settings_header
            )
        )
    }
}

internal fun getChats(@DrawableRes iconId: Int): BottomMenu = BottomMenu(
    route = R.string.chats_destination,
    iconId = iconId,
    headerId = R.string.bottom_menu_chats_header
)

@Composable
internal fun menuViewed(currentRoute: String?): Boolean {
    return currentRoute != null
            && currentRoute != stringResource(id = R.string.onboarding_destination)
            && currentRoute != stringResource(id = R.string.auth_destination)
}

@Composable
internal fun getTitle(currentRoute: String?): String? {
    val chatsRoute = stringResource(id = R.string.chats_destination)
    if (currentRoute == chatsRoute) {
        return stringResource(id = R.string.bottom_menu_chats_header)
    }
    getMenu().forEach { menuItem ->
        val route = stringResource(id = menuItem.route)
        if (route == currentRoute) {
            return stringResource(id = menuItem.headerId)
        }
    }
    return null
}