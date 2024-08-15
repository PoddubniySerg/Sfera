package ru.zavod.app_config

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.zavod.app_config.model.OnboardingParams
import ru.zavod.app_config.utils.toNavigation
import ru.zavod.app_navigation.Navigation

@Composable
fun Configuration(
    @DrawableRes chatsButtonIconId: Int,
    onboardingParams: OnboardingParams,
    lightColorScheme: ColorScheme,
    darkColorScheme: ColorScheme,
    typography: Typography
) {
    AppTheme(
        lightColorScheme = lightColorScheme,
        darkColorScheme = darkColorScheme,
        typography = typography
    ) { setConfig ->
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Navigation(
                chatsButtonIconId = chatsButtonIconId,
                onboardingParams = onboardingParams.toNavigation(),
                setConfig = setConfig
            )
        }
    }
}