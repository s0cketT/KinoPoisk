package com.assistant.absolut.test.kinopoisk.data.repository

import com.assistant.absolut.test.kinopoisk.data.mapper.MapFilmDetailsToDomain
import com.assistant.absolut.test.kinopoisk.data.remote.IFilmDetailsApi
import com.assistant.absolut.test.kinopoisk.domain.common.Resource
import com.assistant.absolut.test.kinopoisk.domain.model.ExceptionDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmDetailsDomainModel
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmDetailsRepository
import com.tasklist.data.extensions.mapToExceptionDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmDetailsRemoteRepositoryImpl(
    private val filmDetailsApi: IFilmDetailsApi,
    private val mapper: MapFilmDetailsToDomain
) : IFilmDetailsRepository {
    override suspend fun getFilmDetails(filmId: Int): Resource<FilmDetailsDomainModel, ExceptionDomainModel> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val apiModelFilmDetails = filmDetailsApi.getFilmDetails(id = filmId)
                val domainModelFilmDetails = mapper.toDomainModel(apiModel = apiModelFilmDetails)
                Resource.Success<FilmDetailsDomainModel, ExceptionDomainModel>(domainModelFilmDetails!!)
            }.getOrElse {
                Resource.Error(it.mapToExceptionDomainModel())
            }
        }
    }
}