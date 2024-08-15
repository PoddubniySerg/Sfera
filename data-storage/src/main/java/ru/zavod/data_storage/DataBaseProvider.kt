package ru.zavod.data_storage

import android.content.Context
import androidx.room.Room
import javax.inject.Inject

class DataBaseProvider @Inject constructor(context: Context) {

    private val applicationContext = context.applicationContext
    private val dataBase: DataBase

    init {
        dataBase = Room.databaseBuilder(
            applicationContext,
            DataBase::class.java,
            name = "app_data_base"
        ).build()
    }

    fun getDataBase(): DataBase = dataBase
}