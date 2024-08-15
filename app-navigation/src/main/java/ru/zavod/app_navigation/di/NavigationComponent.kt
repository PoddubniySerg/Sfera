package ru.zavod.app_navigation.di

import ru.zavod.app_core.viewmodel.ViewModelFactory
import ru.zavod.app_navigation.viewmodel.NavigationViewModel

interface NavigationComponent {
    fun navigationViewModelFactory(): ViewModelFactory<NavigationViewModel>
}