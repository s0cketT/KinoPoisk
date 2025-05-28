package com.assistant.absolut.test.kinopoisk.presentation.film_details


sealed interface FilmDetailsIntent {
    data class GoToUrl(val url: String): FilmDetailsIntent
    data object ShowDialog : FilmDetailsIntent
    data object AddOrRemoveBookmark : FilmDetailsIntent
    data class EnterTempAssess(val tempAssess: String) : FilmDetailsIntent
    data object SaveAssess : FilmDetailsIntent

    data object NavigateBack : FilmDetailsIntent
}