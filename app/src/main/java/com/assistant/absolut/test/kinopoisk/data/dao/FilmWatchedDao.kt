package com.assistant.absolut.test.kinopoisk.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assistant.absolut.test.kinopoisk.data.model.FilmWatchedDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmWatchedDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(filmWatched: FilmWatchedDbModel)

    @Delete
    suspend fun delete(filmWatched: FilmWatchedDbModel)

    @Query("SELECT * FROM film_watched")
    fun getAllFilmWatched(): Flow<List<FilmWatchedDbModel>>

}