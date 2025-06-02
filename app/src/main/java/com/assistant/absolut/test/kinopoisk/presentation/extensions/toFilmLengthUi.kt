package com.assistant.absolut.test.kinopoisk.presentation.extensions

import com.assistant.absolut.test.kinopoisk.R

fun Int?.toFilmLengthUi(): Int = this?.let { minutes ->
    val hours = minutes / 60
    if (hours > 0) {
        R.string.filmLength_hours_minutes
    } else {
        R.string.filmLength_minutes
    }
} ?: R.string.dash



