package ru.zavod.feature_settings.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.zavod.feature_settings.R


@Composable
internal fun Logout(openDialog: Boolean, logout: () -> Unit, close: () -> Unit){
    if (!openDialog){
        return
    }
    AlertDialog(
        onDismissRequest = close,
        confirmButton = {
            TextButton(onClick = logout) {
                Text(
                    text = stringResource(id = R.string.ok_button_title),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        },
        dismissButton = {
            TextButton(onClick = close) {
                Text(
                    text = stringResource(id = R.string.cancel_button_title),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    )
}