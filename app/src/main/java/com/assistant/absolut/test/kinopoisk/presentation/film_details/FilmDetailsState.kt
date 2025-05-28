package com.assistant.absolut.test.kinopoisk.presentation.film_details

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class FilmDetailsState(
    val id: Int,
    val name: String = "",
    val posterUrl: String = "",
    val ratingKinopoisk: Float = 0.0.toFloat(),
    val webUrl: String = "",
    val year: Int = 0,
    val filmLength: String = "",
    val slogan: String = "",
    val shortDescription: String = "",
    val type: Int = 0,
    val ratingAgeLimits: String = "",
    val countries: ImmutableList<String> = persistentListOf(),
    val genres: ImmutableList<String> = persistentListOf(),

    val isLoading: Boolean = false,
    val error: Int? = null,
    val isBookmark: Boolean = false,
    val isDialog: Boolean = false,
    val assess: String? = null,
    val tempAssess: String = ""
)
