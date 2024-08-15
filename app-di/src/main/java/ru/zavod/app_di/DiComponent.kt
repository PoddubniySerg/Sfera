package ru.zavod.app_di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.zavod.app_core.di.AppComponent
import ru.zavod.app_di.module.AuthModule
import ru.zavod.app_di.module.DataApiModule
import ru.zavod.app_di.module.NavigationModule
import ru.zavod.app_navigation.di.NavigationComponent
import ru.zavod.feature_auth.di.AuthComponent
import javax.inject.Scope

@Component(
    modules = [
        AuthModule::class,
        DataApiModule::class,
        NavigationModule::class
    ]
)
@AppScope
interface DiComponent :
    AppComponent,
    AuthComponent,
    NavigationComponent {
    @Component.Factory
    interface AuthComponentFactory {
        fun create(@BindsInstance context: Context): DiComponent
    }
}

@Scope
annotation class AppScope