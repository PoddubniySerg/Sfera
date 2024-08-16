package ru.zavod.feature_profile.utils

import androidx.annotation.StringRes
import ru.zavod.feature_profile.R
import java.time.Month

internal enum class ZodiacEnum(
    @StringRes val titleId: Int,
    val startMonth: Month,
    val startDay: Int,
    val endMonth: Month,
    val endDay: Int
) {
    Aries(
        titleId = R.string.zodiac_aries_title,
        startMonth = Month.MARCH,
        startDay = 21,
        endMonth = Month.APRIL,
        endDay = 20
    ),
    Taurus(
        titleId = R.string.zodiac_taurus_title,
        startMonth = Month.APRIL,
        startDay = 21,
        endMonth = Month.MAY,
        endDay = 21
    ),
    Gemini(
        titleId = R.string.zodiac_gemini_title,
        startMonth = Month.MAY,
        startDay = 22,
        endMonth = Month.JUNE,
        endDay = 21
    ),
    Cancer(
        titleId = R.string.zodiac_cancer_title,
        startMonth = Month.JUNE,
        startDay = 22,
        endMonth = Month.JULY,
        endDay = 22
    ),
    Leo(
        titleId = R.string.zodiac_leo_title,
        startMonth = Month.JULY,
        startDay = 23,
        endMonth = Month.AUGUST,
        endDay = 21
    ),
    Virgo(
        titleId = R.string.zodiac_virgo_title,
        startMonth = Month.AUGUST,
        startDay = 22,
        endMonth = Month.SEPTEMBER,
        endDay = 23
    ),
    Libra(
        titleId = R.string.zodiac_libra_title,
        startMonth = Month.SEPTEMBER,
        startDay = 24,
        endMonth = Month.OCTOBER,
        endDay = 23
    ),
    Scorpio(
        titleId = R.string.zodiac_scorpio_title,
        startMonth = Month.OCTOBER,
        startDay = 24,
        endMonth = Month.NOVEMBER,
        endDay = 22
    ),
    Sagittarius(
        titleId = R.string.zodiac_sagittarius_title,
        startMonth = Month.NOVEMBER,
        startDay = 23,
        endMonth = Month.DECEMBER,
        endDay = 22
    ),
    Capricorn(
        titleId = R.string.zodiac_capricorn_title,
        startMonth = Month.DECEMBER,
        startDay = 23,
        endMonth = Month.JANUARY,
        endDay = 20
    ),
    Aquarius(
        titleId = R.string.zodiac_aquarius_title,
        startMonth = Month.JANUARY,
        startDay = 21,
        endMonth = Month.FEBRUARY,
        endDay = 19
    ),
    Pisces(
        titleId = R.string.zodiac_pisces_title,
        startMonth = Month.FEBRUARY,
        startDay = 20,
        endMonth = Month.MARCH,
        endDay = 20
    )
}