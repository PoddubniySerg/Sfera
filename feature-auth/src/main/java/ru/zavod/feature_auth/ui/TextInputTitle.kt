package ru.zavod.feature_auth.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.zavod.feature_auth.R

@Composable
internal fun TextInputTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge
    )
}