package ru.zavod.data_api

/**
 * @property JWT_TOKEN_HEADER - Заголовок авторизации для фвторизованных запросов к серверу
 * */
internal const val JWT_TOKEN_HEADER = "Authorization"

/**
 * @property JWT_TOKEN_PREFIX - Префикс перед токеном в заголовке авторизации
 * */
internal const val JWT_TOKEN_PREFIX = "Bearer "

/**
 * @property DEFAULT_RESPONSE_CODE - Код ответа по умолчанию, если при получении ответа сервера
 * возникло исключение
 * */
internal const val DEFAULT_RESPONSE_CODE = 0

/**
 * @property UNAUTHORIZED_CODE - Код ответа сервера в случае ошибки авторизации
 * возникло исключение
 * */
internal const val UNAUTHORIZED_CODE = 401