package com.assistant.absolut.test.kinopoisk.domain.repository

import com.assistant.absolut.test.kinopoisk.domain.common.Resource
import com.assistant.absolut.test.kinopoisk.domain.model.ExceptionDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmDetailsDomainModel

interface IFilmDetailsRepository {
    suspend fun getFilmDetails(filmId: Int): Resource<FilmDetailsDomainModel, ExceptionDomainModel>
}