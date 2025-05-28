package com.assistant.absolut.test.kinopoisk.presentation.film_details


sealed interface FilmDetailsEvent {
     data object NavigateBack : FilmDetailsEvent
     class GoToUrl(val url: String) : FilmDetailsEvent
}

