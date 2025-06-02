package com.assistant.absolut.test.kinopoisk.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.assistant.absolut.test.kinopoisk.data.dao.FilmBookmarkDao
import com.assistant.absolut.test.kinopoisk.data.dao.FilmWatchedDao
import com.assistant.absolut.test.kinopoisk.data.model.FilmBookmarkDbModel
import com.assistant.absolut.test.kinopoisk.data.model.FilmWatchedDbModel

@Database(
    entities = [FilmWatchedDbModel::class, FilmBookmarkDbModel::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class KinoPoiskDB : RoomDatabase() {
    abstract val filmWatchedDao: FilmWatchedDao
    abstract val filmBookmarkDao: FilmBookmarkDao
}
