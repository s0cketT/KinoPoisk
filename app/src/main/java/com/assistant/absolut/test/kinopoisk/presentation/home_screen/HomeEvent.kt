package com.assistant.absolut.test.kinopoisk.presentation.home_screen

sealed interface HomeEvent {
    class NavigateToFilmDetails(val filmId: Int) : HomeEvent
}