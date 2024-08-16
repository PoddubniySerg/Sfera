package ru.zavod.feature_profile.di

import ru.zavod.app_core.viewmodel.ViewModelFactory
import ru.zavod.feature_profile.viewmodel.ProfileViewModel

interface ProfileComponent {
    fun profileViewModelFactory(): ViewModelFactory<ProfileViewModel>
}