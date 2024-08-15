package ru.zavod.app_di.module

import dagger.Module
import dagger.Provides
import ru.zavod.app_core.di.ConfigurationProvider
import ru.zavod.app_di.impl.ConfigurationProviderImpl
import ru.zavod.app_di.impl.NavigateApiImpl
import ru.zavod.app_di.impl.TokenProviderImpl
import ru.zavod.app_navigation.di.NavigateApi
import ru.zavod.app_navigation.di.TokenProvider

@Module
class NavigationModule {

    @Provides
    fun providesTokenProvider(tokenProvider: TokenProviderImpl): TokenProvider = tokenProvider

    @Provides
    fun providesConfigurationProvider(
        configurationProvider: ConfigurationProviderImpl
    ): ConfigurationProvider {
        return configurationProvider
    }

    @Provides
    fun providesNavigateApiProvider(navigation: NavigateApiImpl): NavigateApi = navigation
}