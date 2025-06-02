package com.assistant.absolut.test.kinopoisk.domain.use_case

import com.assistant.absolut.test.kinopoisk.domain.model.FilmWatchedDomainModel
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmWatchedRepository
import kotlinx.coroutines.flow.SharedFlow


class GetAllFilmWatchedUseCase(private val filmWatchedRepository: IFilmWatchedRepository) {
    operator fun invoke(): SharedFlow<List<FilmWatchedDomainModel>> {
        return filmWatchedRepository.getAllFilmWatched
    }
}
