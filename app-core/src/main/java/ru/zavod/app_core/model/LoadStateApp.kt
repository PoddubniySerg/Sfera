package ru.zavod.app_core.model

sealed class LoadStateApp {
    data object Loading : LoadStateApp()
    data object Success : LoadStateApp()
    class Failed(val throwable: Throwable?) : LoadStateApp()
}