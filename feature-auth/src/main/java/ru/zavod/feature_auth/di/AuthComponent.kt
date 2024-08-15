package ru.zavod.feature_auth.di

import ru.zavod.app_core.viewmodel.ViewModelFactory
import ru.zavod.feature_auth.viewmodel.AuthViewModel
import ru.zavod.feature_auth.viewmodel.CheckAuthCodeViewModel
import ru.zavod.feature_auth.viewmodel.RegisterViewModel

interface AuthComponent {
    fun authViewModelFactory(): ViewModelFactory<AuthViewModel>
    fun checkAuthCodeViewModelFactory(): ViewModelFactory<CheckAuthCodeViewModel>
    fun registerViewModelFactory(): ViewModelFactory<RegisterViewModel>
}