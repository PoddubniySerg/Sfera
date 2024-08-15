package ru.zavod.app_config

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.zavod.app_core.model.Configuration

@Composable
internal fun AppTheme(
    lightColorScheme: ColorScheme,
    darkColorScheme: ColorScheme,
    typography: Typography,
    content: @Composable ((Configuration) -> Unit) -> Unit
) {
    val viewModel = viewModel<ConfigViewModel>()
    val config by viewModel.configStateFlow.collectAsState()
    val darkTheme = darkTheme(configuration = config)
    val colorScheme = when {
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = { content(viewModel::setConfig) }
    )
}

@Composable
private fun darkTheme(configuration: Configuration?): Boolean {
    return when {
        configuration?.theme == Configuration.ApplicationTheme.DARK -> true
        configuration?.theme == Configuration.ApplicationTheme.LIGHT -> false
        else -> isSystemInDarkTheme()
    }
}