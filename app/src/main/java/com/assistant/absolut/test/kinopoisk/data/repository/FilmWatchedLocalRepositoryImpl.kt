package com.assistant.absolut.test.kinopoisk.data.repository

import com.assistant.absolut.test.kinopoisk.data.dao.FilmWatchedDao
import com.assistant.absolut.test.kinopoisk.data.mapper.MapFilmWatched
import com.assistant.absolut.test.kinopoisk.domain.model.FilmWatchedDomainModel
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmWatchedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.plus


class FilmWatchedLocalRepositoryImpl(
    private val dao: FilmWatchedDao,
    private val mapper: MapFilmWatched
) : IFilmWatchedRepository {

    override val getAllFilmWatched: SharedFlow<List<FilmWatchedDomainModel>> = dao.getAllFilmWatched()
        .map { mapper.toDomainModel(dbModel = it) }
        .buffer(onBufferOverflow = BufferOverflow.DROP_OLDEST)
        .distinctUntilChanged()
        .shareIn(
            scope = MainScope() + Dispatchers.IO,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 10_000, replayExpirationMillis = 0),
            replay = 1
        )

    override suspend fun insertFilmWatched(filmWatched: FilmWatchedDomainModel) {
        dao.insert(mapper.fromDomainModel(filmWatched))
    }

    override suspend fun deleteFilmWatched(filmWatched: FilmWatchedDomainModel) {
        dao.delete(mapper.fromDomainModel(filmWatched))
    }

}