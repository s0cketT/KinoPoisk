package com.assistant.absolut.test.kinopoisk.presentation.extensions


import com.assistant.absolut.test.kinopoisk.R
import com.assistant.absolut.test.kinopoisk.domain.model.FilmTypeDomainModel

fun FilmTypeDomainModel.filmTypeStringToUi(): Int {
    return when (this) {
        FilmTypeDomainModel.FILM -> R.string.type_film
        FilmTypeDomainModel.VIDEO -> R.string.type_video
        FilmTypeDomainModel.TV_SERIES -> R.string.type_tv_series
        FilmTypeDomainModel.MINI_SERIES -> R.string.type_mini_series
        FilmTypeDomainModel.TV_SHOW -> R.string.type_tv_show
    }
}

