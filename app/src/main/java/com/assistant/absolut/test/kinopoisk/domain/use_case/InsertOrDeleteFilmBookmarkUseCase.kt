package com.assistant.absolut.test.kinopoisk.domain.use_case

import com.assistant.absolut.test.kinopoisk.domain.model.FilmBookmarkDomainModel
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmBookmarkRepository


class InsertOrDeleteFilmBookmarkUseCase(private val filmBookmarkRepository: IFilmBookmarkRepository) {
    suspend operator fun invoke(filmBookmark: FilmBookmarkDomainModel, isBookmark: Boolean)  {
        if (isBookmark) {
            filmBookmarkRepository.insertFilmBookmark(filmBookmark)
        } else {
            filmBookmarkRepository.deleteFilmBookmark(filmBookmark)
        }
    }
}
