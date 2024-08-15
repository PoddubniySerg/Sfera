package ru.zavod.feature_chats.utils

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

private const val PREFIX = "#"
private const val HEX_SIZE = 6

private val charList =
    arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')

internal fun getRandomColor(): String {
    val stringBuilder = StringBuilder(PREFIX)
    repeat(times = HEX_SIZE) {
        stringBuilder.append(charList[Random.nextInt(charList.size)])
    }
    return stringBuilder.toString()
}

internal fun getColorFromHexStrng(hex: String): Color? {
    return try {
        Color(android.graphics.Color.parseColor(hex))
    } catch (_: Exception) {
        null
    }
}