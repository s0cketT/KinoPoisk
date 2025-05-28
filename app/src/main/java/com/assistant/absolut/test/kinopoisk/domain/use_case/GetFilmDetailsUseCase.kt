package com.assistant.absolut.test.kinopoisk.domain.use_case

import com.assistant.absolut.test.kinopoisk.domain.common.Resource
import com.assistant.absolut.test.kinopoisk.domain.model.ExceptionDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmDetailsDomainModel
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmDetailsRepository


class GetFilmDetailsUseCase(private val filmDetailsRepository: IFilmDetailsRepository) {
    suspend operator fun invoke(filmId: Int): Resource<FilmDetailsDomainModel, ExceptionDomainModel> {
        return filmDetailsRepository.getFilmDetails(filmId = filmId)
    }
}