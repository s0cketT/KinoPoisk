package com.assistant.absolut.test.kinopoisk.domain.use_case

import com.assistant.absolut.test.kinopoisk.domain.common.Resource
import com.assistant.absolut.test.kinopoisk.domain.model.ExceptionDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmsDomainModel
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmBookmarkRepository
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmWatchedRepository
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmsRepository
import kotlinx.coroutines.flow.firstOrNull

class GetFilmsUseCase(
    private val filmsRepository: IFilmsRepository,
    private val getAllFilmWatchedUseCase: IFilmWatchedRepository,
    private val getAllFilmBookmarkUseCase: IFilmBookmarkRepository
    ) {
    suspend operator fun invoke(page: Int): Resource<FilmsDomainModel, ExceptionDomainModel> {
        return when (val result = filmsRepository.getFilms(page = page)) {
            is Resource.Success -> {
                val watchedList = getAllFilmWatchedUseCase.getAllFilmWatched.firstOrNull().orEmpty()
                val bookmarkList = getAllFilmBookmarkUseCase.getAllFilmBookmark.firstOrNull().orEmpty()

                val updatedFilms = result.data.films.map { film ->
                    val isBookmarked = bookmarkList.any { it.idFilm == film.id }
                    val watchedFilm = watchedList.find { it.idFilm == film.id }
                    film.copy(
                        isBookmark = isBookmarked,
                        assess = watchedFilm?.assessUser
                    )
                }

                Resource.Success(result.data.copy(films = updatedFilms))
            }

            is Resource.Error -> result
        }
    }
}