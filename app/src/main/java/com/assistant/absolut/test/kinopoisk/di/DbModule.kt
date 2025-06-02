package com.assistant.absolut.test.kinopoisk.di

import com.assistant.absolut.test.kinopoisk.data.common.DbFactory
import com.assistant.absolut.test.kinopoisk.data.dao.FilmBookmarkDao
import com.assistant.absolut.test.kinopoisk.data.dao.FilmWatchedDao
import com.assistant.absolut.test.kinopoisk.data.local.KinoPoiskDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val databaseModule = module {

    single<KinoPoiskDB> {
        DbFactory.createRoomDatabase(context = androidApplication())
    }

    single<FilmWatchedDao> {
        get<KinoPoiskDB>().filmWatchedDao
    }

    single<FilmBookmarkDao> {
        get<KinoPoiskDB>().filmBookmarkDao
    }
}
