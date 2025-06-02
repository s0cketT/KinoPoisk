package com.assistant.absolut.test.kinopoisk.data.common

import android.content.Context
import androidx.room.Room
import com.assistant.absolut.test.kinopoisk.data.local.KinoPoiskDB


object DbFactory {
    fun createRoomDatabase(context: Context): KinoPoiskDB {
        return Room.databaseBuilder(
            context,
            KinoPoiskDB::class.java,
            "KinoPoisk.db"
        )
            .build()
    }
}
