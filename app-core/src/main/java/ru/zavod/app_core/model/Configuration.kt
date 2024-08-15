package ru.zavod.app_core.model

import androidx.annotation.StringRes
import ru.zavod.app_core.R

data class Configuration(val theme: ApplicationTheme = ApplicationTheme.SYSTEM) {
    enum class ApplicationTheme(@StringRes val titleId: Int) {
        LIGHT(titleId = R.string.config_theme_light_title),
        DARK(titleId = R.string.config_theme_dark_title),
        SYSTEM(titleId = R.string.config_theme_systemic_title)
    }
}
