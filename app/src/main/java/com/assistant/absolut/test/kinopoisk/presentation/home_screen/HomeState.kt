package com.assistant.absolut.test.kinopoisk.presentation.home_screen

import com.assistant.absolut.test.kinopoisk.domain.model.FilmsDomainModel

data class HomeState(
    val isLoading: Boolean = false,
    val films: List<FilmsDomainModel.Film> = emptyList(),
    val error: Int? = null,
    val nextPage: Int = 1,
    val maxPage: Int = 1,
    val pageSize: Int = 0
)
{
    val isHaveNextPage = nextPage <= maxPage
}
