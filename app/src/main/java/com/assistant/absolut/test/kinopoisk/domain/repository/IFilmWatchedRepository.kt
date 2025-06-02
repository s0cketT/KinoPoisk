package com.assistant.absolut.test.kinopoisk.domain.repository

import com.assistant.absolut.test.kinopoisk.domain.model.FilmWatchedDomainModel
import kotlinx.coroutines.flow.SharedFlow

interface IFilmWatchedRepository {

    val getAllFilmWatched: SharedFlow<List<FilmWatchedDomainModel>>
    suspend fun insertFilmWatched(filmWatched: FilmWatchedDomainModel)
    suspend fun deleteFilmWatched(filmWatched: FilmWatchedDomainModel)

}