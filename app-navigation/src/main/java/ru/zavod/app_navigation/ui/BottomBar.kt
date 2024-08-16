package ru.zavod.app_navigation.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.zavod.app_navigation.SpaceChatsButton
import ru.zavod.app_navigation.TonalElevationLvl1
import ru.zavod.app_navigation.navigateToRoute

@Composable
internal fun BottomBar(
    navController: NavHostController
) {

    if (!menuViewed(controller = navController)) {
        return
    }

    val menu = getMenu()
    NavigationBar(tonalElevation = TonalElevationLvl1) {
        val divideIndex = menu.size / 2
        menu.forEachIndexed { index, item ->
            if (index > divideIndex) {
                Spacer(modifier = Modifier.size(size = SpaceChatsButton))
            }
            MenuItem(
                item = item,
                navController = navController
            )
        }
    }
}

@Composable
private fun RowScope.MenuItem(
    item: BottomMenu,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val isSelected = navBackStackEntry?.destination
        ?.hierarchy
        ?.any { it.route == item.destination.destination } == true

    val selectedColor = MaterialTheme.colorScheme.primary
    val unselectedColor = MaterialTheme.colorScheme.outlineVariant
    NavigationBarItem(
        icon = {
            Icon(
                modifier = Modifier.size(22.dp),
                painter = painterResource(id = item.iconId),
                contentDescription = null
            )
        },
        selected = isSelected,
        onClick = {
            navigateToRoute(navController = navController, itemRoute = item.destination.destination)
        },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Transparent,
            selectedIconColor = selectedColor,
            selectedTextColor = selectedColor,
            unselectedIconColor = unselectedColor,
            unselectedTextColor = unselectedColor,
            disabledIconColor = unselectedColor.copy(alpha = 0.3f),
            disabledTextColor = unselectedColor.copy(alpha = 0.3f)
        )
    )
}

@Preview
@Composable
private fun BottomMenuPreview() {
    BottomBar(navController = rememberNavController())
}