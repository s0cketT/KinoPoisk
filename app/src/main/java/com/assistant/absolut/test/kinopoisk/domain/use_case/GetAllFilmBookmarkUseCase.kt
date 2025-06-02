package com.assistant.absolut.test.kinopoisk.domain.use_case

import com.assistant.absolut.test.kinopoisk.domain.model.FilmBookmarkDomainModel
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmBookmarkRepository

import kotlinx.coroutines.flow.SharedFlow


class GetAllFilmBookmarkUseCase(private val filmBookmarkRepository: IFilmBookmarkRepository) {
    operator fun invoke(): SharedFlow<List<FilmBookmarkDomainModel>> {
        return filmBookmarkRepository.getAllFilmBookmark
    }
}
