package com.assistant.absolut.test.kinopoisk.presentation.home_screen

import com.assistant.absolut.test.kinopoisk.domain.model.FilmsDomainModel

data class HomeState(
    val isLoading: Boolean = true,
    val isAppending: Boolean = false,
    val films: List<FilmsDomainModel.Film> = emptyList(),
    val error: Int? = null,
    val currentPage: Int = 1,
    val maxPage: Int = 0,
    val pageSize: Int = 0
)
