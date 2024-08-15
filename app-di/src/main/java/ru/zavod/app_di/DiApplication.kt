package ru.zavod.app_di

import android.app.Application
import ru.zavod.app_core.di.AppComponent
import ru.zavod.app_core.di.AppComponentProvider

open class DiApplication : Application(), AppComponentProvider {

    protected lateinit var diComponent: DiComponent
    override fun getAppComponent(): AppComponent = diComponent

    override fun onCreate() {
        super.onCreate()
        diComponent = AppComponentProviderImpl(applicationContext = this).getAppComponent()
    }
}