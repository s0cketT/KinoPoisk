package com.assistant.absolut.test.kinopoisk.domain.model

data class FilmWatchedDomainModel(
    val idFilm: Int,
    val assessUser: Int?,
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
