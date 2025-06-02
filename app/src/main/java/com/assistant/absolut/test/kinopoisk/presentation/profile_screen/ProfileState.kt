package com.assistant.absolut.test.kinopoisk.presentation.profile_screen

import com.assistant.absolut.test.kinopoisk.domain.model.FilmBookmarkDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmWatchedDomainModel

data class ProfileState(
    val filmsWatched: List<FilmWatchedDomainModel> = emptyList(),
    val filmsBookmark: List<FilmBookmarkDomainModel> = emptyList(),
    val filmsFilter: FilmsFilter = FilmsFilter.WATCHED,
)
