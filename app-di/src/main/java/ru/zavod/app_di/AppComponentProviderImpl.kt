package ru.zavod.app_di

import android.content.Context
import ru.zavod.app_core.di.AppComponentProvider

class AppComponentProviderImpl(private val applicationContext: Context) : AppComponentProvider {

    override fun getAppComponent(): DiComponent =
        DaggerDiComponent.factory().create(applicationContext)
}