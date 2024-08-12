package ru.zavod.data_storage.di

interface Token {
    val access: String
    val refresh: String
    val userExist: Boolean
}