package com.assistant.absolut.test.kinopoisk.data.repository

import com.assistant.absolut.test.kinopoisk.data.mapper.MapFilmsToDomain
import com.assistant.absolut.test.kinopoisk.data.remote.IFilmsApi
import com.assistant.absolut.test.kinopoisk.domain.common.Resource
import com.assistant.absolut.test.kinopoisk.domain.model.ExceptionDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmsDomainModel
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmsRepository
import com.tasklist.data.extensions.mapToExceptionDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmsRepositoryImpl(
    private val filmsApi: IFilmsApi,
    private val mapper: MapFilmsToDomain
): IFilmsRepository {

    override suspend fun getFilms(page: Int): Resource<FilmsDomainModel, ExceptionDomainModel> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val apiModelFilms = filmsApi.getFilms(page = page)
                val domainModelFilms = mapper.toDomainModel(apiModel = apiModelFilms)
                Resource.Success<FilmsDomainModel, ExceptionDomainModel>(domainModelFilms!!)
            }.getOrElse {
                Resource.Error(it.mapToExceptionDomainModel())
            }
        }
    }

}