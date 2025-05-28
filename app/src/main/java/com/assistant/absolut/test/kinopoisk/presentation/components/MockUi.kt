package com.assistant.absolut.test.kinopoisk.presentation.components

import com.assistant.absolut.test.kinopoisk.domain.model.FilmsDomainModel

val mockFilm = FilmsDomainModel.Film(
    id = 0,
    name = "",
    countries = emptyList(),
    ratingKinopoisk = 0.2.toFloat(),
    year = 0,
    posterUrlPreview = ""
)