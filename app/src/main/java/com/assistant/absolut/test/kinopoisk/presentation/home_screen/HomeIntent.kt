package com.assistant.absolut.test.kinopoisk.presentation.home_screen


sealed interface HomeIntent {
    data object LoadingNextPages : HomeIntent
    data class NavigateToFilmDetailsScreen(val filmId: Int, val assess: Int?, val isBookmark: Boolean): HomeIntent
}