package com.assistant.absolut.test.kinopoisk.presentation.home_screen


sealed interface HomeIntent {
    data object LoadingNextPages : HomeIntent
}