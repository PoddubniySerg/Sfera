package ru.zavod.app_navigation.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.zavod.app_navigation.OffsetYChatsButton
import ru.zavod.app_navigation.PaddingLvl1
import ru.zavod.app_navigation.PaddingLvl2
import ru.zavod.app_navigation.SizeChatsButton
import ru.zavod.app_navigation.VerticalOffsetChatsButton

@Composable
internal fun ChatsMenuButton(
    controller: NavHostController,
    @DrawableRes iconId: Int,
    onClick: (String) -> Unit
) {

    if (!menuViewed(controller = controller)) {
        return
    }

    val button = getChats(iconId = iconId)

    Box(
        modifier = Modifier
            .offset(y = VerticalOffsetChatsButton)
            .size(size = SizeChatsButton)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.onSurface,
                        Color.Transparent
                    )
                ),
                shape = CircleShape
            )
    ) {

        val navBackStackEntry by controller.currentBackStackEntryAsState()
        val isSelected = navBackStackEntry?.destination
            ?.hierarchy
            ?.any { it.route == button.destination.destination } == true
        val tint = when {
            isSelected -> Color.Unspecified
            else -> MaterialTheme.colorScheme.outlineVariant
        }
        IconButton(
            onClick = { onClick(button.destination.destination) },
            modifier = Modifier
                .fillMaxSize()
                .padding(all = PaddingLvl1)
                .offset(y = OffsetYChatsButton),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Icon(
                painter = painterResource(id = button.iconId),
                contentDescription = null,
                tint = tint,
                modifier = Modifier.padding(all = PaddingLvl2)
            )
        }
    }
}