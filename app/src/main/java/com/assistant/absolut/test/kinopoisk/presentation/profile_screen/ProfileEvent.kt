package com.assistant.absolut.test.kinopoisk.presentation.profile_screen

sealed interface ProfileEvent {
    class NavigateToFilmDetails(val filmId: Int, val assess: Int?, val isBookmark: Boolean) : ProfileEvent
}