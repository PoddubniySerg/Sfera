package ru.zavod.feature_settings.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.zavod.feature_settings.PaddingLvl2
import ru.zavod.feature_settings.R


@Composable
internal fun LogoutButton(onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Icon(painter = painterResource(id = R.drawable.logout_icon), contentDescription = null)
        Spacer(modifier = Modifier.width(width = PaddingLvl2))
        Text(
            text = stringResource(id = R.string.sections_logout_button_title),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}