package ru.zavod.teplits.ui.theme

import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import ru.zavod.teplits.R

private const val PROVIDER_AUTHORITY = "com.google.android.gms.fonts"
private const val PROVIDER_PACKAGE = "com.google.android.gms"
private const val FONT_FAMILY = "Roboto"

@OptIn(ExperimentalTextApi::class)
private val Provider = GoogleFont.Provider(
    providerAuthority = PROVIDER_AUTHORITY,
    providerPackage = PROVIDER_PACKAGE,
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val Font = GoogleFont(name = FONT_FAMILY)

val DefaultFontFamily = FontFamily(
    Font(googleFont = Font, fontProvider = Provider),
    Font(googleFont = Font, fontProvider = Provider, weight = FontWeight.Normal),
    Font(googleFont = Font, fontProvider = Provider, weight = FontWeight.Bold),
    Font(googleFont = Font, fontProvider = Provider, weight = FontWeight.Medium)

)