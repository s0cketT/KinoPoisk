package com.assistant.absolut.test.kinopoisk.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assistant.absolut.test.kinopoisk.data.model.FilmBookmarkDbModel
import kotlinx.coroutines.flow.Flow



@Dao
interface FilmBookmarkDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(filmBookmark: FilmBookmarkDbModel)

    @Delete
    suspend fun delete(filmBookmark: FilmBookmarkDbModel)

    @Query("SELECT * FROM film_bookmark")
    fun getAllFilmBookmark(): Flow<List<FilmBookmarkDbModel>>

}
