package ru.zavod.app_navigation.route

internal enum class Destination(val destination: String){
    ONBOARDING(destination = "onboarding"),
    AUTH(destination = "auth"),
    CHATS(destination = "chats"),
    PROFILE(destination = "profile"),
    SETTINGS(destination = "settings"),
}