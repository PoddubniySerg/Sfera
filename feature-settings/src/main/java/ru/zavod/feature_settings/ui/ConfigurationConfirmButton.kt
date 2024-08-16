package ru.zavod.feature_settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.zavod.app_core.FULL_WEIGTH
import ru.zavod.feature_settings.PaddingLvl3
import ru.zavod.feature_settings.R
import ru.zavod.feature_settings.WidthLvl1
import ru.zavod.feature_settings.WidthLvl2

@Composable
internal fun ConfigurationConfirmButton(enabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .widthIn(min = WidthLvl1, max = WidthLvl2)
            .padding(horizontal = PaddingLvl3)
    ) {
        LeadingIcon()
        FullWeightSpacer()
        Title()
        FullWeightSpacer()
    }
}

@Composable
private fun LeadingIcon() {
    Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
}

@Composable
private fun Title() {
    Text(
        text = stringResource(id = R.string.configuration_confirm_button_title),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun RowScope.FullWeightSpacer() {
    Spacer(modifier = Modifier.weight(weight = FULL_WEIGTH))
}

@Preview(showBackground = true)
@Composable
private fun ConfigurationConfirmButtonPreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        ConfigurationConfirmButton(enabled = true, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun ConfigurationConfirmButtonDisabledPreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        ConfigurationConfirmButton(enabled = false, onClick = {})
    }
}