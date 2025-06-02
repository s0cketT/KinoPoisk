package com.assistant.absolut.test.kinopoisk.presentation.profile_screen

sealed interface ProfileIntent {
    data class SwitchFilterFilms(val filter: FilmsFilter): ProfileIntent
    data class NavigateToFilmDetailsScreen(val filmId: Int): ProfileIntent
}