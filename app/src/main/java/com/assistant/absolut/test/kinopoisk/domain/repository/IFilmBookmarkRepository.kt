package com.assistant.absolut.test.kinopoisk.domain.repository

import com.assistant.absolut.test.kinopoisk.domain.model.FilmBookmarkDomainModel
import kotlinx.coroutines.flow.SharedFlow


interface IFilmBookmarkRepository {

    val getAllFilmBookmark: SharedFlow<List<FilmBookmarkDomainModel>>
    suspend fun insertFilmBookmark(filmBookmark: FilmBookmarkDomainModel)
    suspend fun deleteFilmBookmark(filmBBookmark: FilmBookmarkDomainModel)

}
