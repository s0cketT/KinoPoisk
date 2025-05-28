package com.assistant.absolut.test.kinopoisk.presentation.extensions

fun Int?.toRatingAgeLimitsUi(): String = this?.let { age ->
    "$age+"
} ?: "—"