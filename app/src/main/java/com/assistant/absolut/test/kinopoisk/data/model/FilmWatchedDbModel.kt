package com.assistant.absolut.test.kinopoisk.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_watched")
data class FilmWatchedDbModel(
    @PrimaryKey
    val idFilm: Int,
    val assessUser: Int,
    val name: String,
    val genres: List<String>,
    val hours: Int?,
    val minutes: Int?,
    val countries: List<String>,
    val ratingKinopoisk: Float,
    val year: Int,
    val posterUrlPreview: String,
    val ratingKinopoiskVoteCount: Int
)
