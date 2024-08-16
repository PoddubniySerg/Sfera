package ru.zavod.feature_profile.utils

import java.time.LocalDate

internal fun getZodiac(birthday: String): ZodiacEnum? {
    return try {
        val date = LocalDate.parse(birthday)
        val month = date.month
        val day = date.dayOfMonth
        ZodiacEnum.entries.firstOrNull {
            it.startMonth == month && it.startDay <= day || it.endMonth == month && it.endDay >= day
        }
    } catch (_: Exception) {
        null
    }
}