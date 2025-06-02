package com.assistant.absolut.test.kinopoisk.presentation.components

import com.assistant.absolut.test.kinopoisk.domain.model.FilmBookmarkDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmWatchedDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmsDomainModel

val mockFilm = FilmsDomainModel.Film(
    id = 0,
    name = "",
    countries = emptyList(),
    ratingKinopoisk = 0.2.toFloat(),
    year = 0,
    posterUrlPreview = "",
    assess = null,
    isBookmark = false
)

val mockFilmWatched = FilmWatchedDomainModel(
    idFilm = 0,
    assessUser = 0,
    name = "",
    genres = emptyList(),
    hours = 0,
    minutes = 0,
    countries = emptyList(),
    ratingKinopoisk = 0.toFloat(),
    year = 0,
    posterUrlPreview = "",
    ratingKinopoiskVoteCount = 0
)

val mockFilmBookmark = FilmBookmarkDomainModel(
    idFilm = 0,
    name = "",
    genres = emptyList(),
    hours = 0,
    minutes = 0,
    countries = emptyList(),
    ratingKinopoisk = 0.toFloat(),
    year = 0,
    posterUrlPreview = ""
)