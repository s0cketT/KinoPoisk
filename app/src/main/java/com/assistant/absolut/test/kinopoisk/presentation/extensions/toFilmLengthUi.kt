package com.assistant.absolut.test.kinopoisk.presentation.extensions

fun Int?.toFilmLengthUi(): String = this?.let { minutes ->
    val hours = minutes / 60
    val remainingMinutes = minutes % 60
    if (hours > 0) {
        "$hours ч $remainingMinutes мин"
    } else {
        "$remainingMinutes мин"
    }
} ?: "—"