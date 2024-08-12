package ru.zavod.data_api.dto

internal interface ThrowableResponse {
    val errors: List<ErrorDto>?
}