package com.assistant.absolut.test.kinopoisk.domain.use_case

import com.assistant.absolut.test.kinopoisk.domain.model.FilmWatchedDomainModel
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmWatchedRepository

class InsertOrDeleteFilmWatchedUseCase(private val filmWatchedRepository: IFilmWatchedRepository) {
    suspend operator fun invoke(filmWatched: FilmWatchedDomainModel)  {
        if (filmWatched.assessUser == null) {
            filmWatchedRepository.deleteFilmWatched(filmWatched) }
        else {
            filmWatchedRepository.insertFilmWatched(filmWatched)
        }
    }
}