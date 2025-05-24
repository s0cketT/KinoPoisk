package com.assistant.absolut.test.kinopoisk.domain.repository

import com.assistant.absolut.test.kinopoisk.domain.common.Resource
import com.assistant.absolut.test.kinopoisk.domain.model.ExceptionDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmsDomainModel

interface IFilmsRepository {
    suspend fun getFilms(page: Int): Resource<FilmsDomainModel, ExceptionDomainModel>
}