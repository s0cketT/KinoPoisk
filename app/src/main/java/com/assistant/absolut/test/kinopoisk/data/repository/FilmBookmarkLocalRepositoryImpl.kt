package com.assistant.absolut.test.kinopoisk.data.repository


import com.assistant.absolut.test.kinopoisk.data.dao.FilmBookmarkDao
import com.assistant.absolut.test.kinopoisk.data.mapper.MapFilmBookmark
import com.assistant.absolut.test.kinopoisk.domain.model.FilmBookmarkDomainModel
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmBookmarkRepository
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



class FilmBookmarkLocalRepositoryImpl(
    private val dao: FilmBookmarkDao,
    private val mapper: MapFilmBookmark
) : IFilmBookmarkRepository {

    override val getAllFilmBookmark: SharedFlow<List<FilmBookmarkDomainModel>> = dao.getAllFilmBookmark()
        .map { mapper.toDomainModel(dbModel = it) }
        .buffer(onBufferOverflow = BufferOverflow.DROP_OLDEST)
        .distinctUntilChanged()
        .shareIn(
            scope = MainScope() + Dispatchers.IO,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 10_000, replayExpirationMillis = 0),
            replay = 1
        )

    override suspend fun insertFilmBookmark(filmBookmark: FilmBookmarkDomainModel) {
        dao.insert(mapper.fromDomainModel(filmBookmark))
    }

    override suspend fun deleteFilmBookmark(filmBookmark: FilmBookmarkDomainModel) {
        dao.delete(mapper.fromDomainModel(filmBookmark))
    }

}
