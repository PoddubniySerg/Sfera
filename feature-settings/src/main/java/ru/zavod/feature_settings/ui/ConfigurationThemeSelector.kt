package ru.zavod.feature_settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.zavod.app_core.FULL_WEIGTH
import ru.zavod.app_core.model.Configuration
import ru.zavod.feature_settings.PaddingLvl1
import ru.zavod.feature_settings.PaddingLvl3
import ru.zavod.feature_settings.R

@Composable
internal fun ConfigurationThemeSelector(
    configuration: Configuration?,
    setConfiguration: (Configuration?) -> Unit
) {
    Column {
        Title()
        Spacer(modifier = Modifier.height(height = PaddingLvl3))
        SelectButtons(configuration = configuration, setConfiguration = setConfiguration)
    }
}

@Composable
private fun Title() {
    Text(
        text = stringResource(id = R.string.configuration_theme_selector_title),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun SelectButtons(
    configuration: Configuration?,
    setConfiguration: (Configuration?) -> Unit
) {
    Configuration.ApplicationTheme.entries.forEach {
        SelectButton(
            selected = it == configuration?.theme,
            title = stringResource(id = it.titleId),
            onClick = {
                val updated = configuration?.copy(theme = it)
                setConfiguration(updated)
            }
        )
    }
}

@Composable
private fun SelectButton(selected: Boolean, title: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        contentPadding = PaddingValues(all = PaddingLvl1)
    ) {
        RadioButton(selected = selected, onClick = {})
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.weight(weight = FULL_WEIGTH))
    }
}

@Preview(showBackground = true)
@Composable
private fun ConfigurationThemeSelectorPreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        ConfigurationThemeSelector(
            configuration = Configuration(theme = Configuration.ApplicationTheme.DARK),
            setConfiguration = {})
    }
}