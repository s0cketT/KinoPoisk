package com.assistant.absolut.test.kinopoisk.domain.use_case

import com.assistant.absolut.test.kinopoisk.domain.common.Resource
import com.assistant.absolut.test.kinopoisk.domain.model.ExceptionDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmsDomainModel
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmsRepository

class GetFilmsUseCase(private val filmsRepository: IFilmsRepository) {
    suspend operator fun invoke(page: Int): Resource<FilmsDomainModel, ExceptionDomainModel> {
        return filmsRepository.getFilms(page = page)
    }
}