package ru.zavod.teplits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.zavod.app_config.Configuration
import ru.zavod.app_config.model.LottieAnimationParams
import ru.zavod.app_config.model.OnboardingParams
import ru.zavod.teplits.ui.theme.Pink40
import ru.zavod.teplits.ui.theme.Pink80
import ru.zavod.teplits.ui.theme.Purple40
import ru.zavod.teplits.ui.theme.Purple80
import ru.zavod.teplits.ui.theme.PurpleGrey40
import ru.zavod.teplits.ui.theme.PurpleGrey80
import ru.zavod.teplits.ui.theme.Typography

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

private val Animation = LottieAnimationParams(
    animationId = R.raw.start_animated_logo,
    millis = 5500
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Configuration(
                chatsButtonIconId = R.drawable.chats_button_icon,
                onboardingParams = OnboardingParams(animation = Animation),
                lightColorScheme = lightColorScheme(),
                darkColorScheme = darkColorScheme(),
                typography = Typography
            )
        }
    }
}