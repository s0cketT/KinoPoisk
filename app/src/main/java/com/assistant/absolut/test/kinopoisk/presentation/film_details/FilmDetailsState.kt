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
    val filmLength: Int = 0,
    val hours: Int? = null,
    val minutes: Int? = null,
    val slogan: String = "",
    val shortDescription: String = "",
    val type: Int = 0,
    val ratingAgeLimits: Int? = null,
    val countries: ImmutableList<String> = persistentListOf(),
    val genres: ImmutableList<String> = persistentListOf(),
    val ratingKinopoiskVoteCount: Int = 0,

    val isLoading: Boolean = false,
    val error: Int? = null,
    val isBookmark: Boolean,
    val isDialog: Boolean = false,
    val assess: Int?,
    val tempAssess: String = ""
)
