package com.assistant.absolut.test.kinopoisk.data.mapper

import com.assistant.absolut.test.kinopoisk.data.model.FilmWatchedDbModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmWatchedDomainModel

class MapFilmWatched {
    fun toDomainModel(dbModel: List<FilmWatchedDbModel>): List<FilmWatchedDomainModel> {
        return dbModel.map {
            FilmWatchedDomainModel(
                idFilm = it.idFilm,
                assessUser = it.assessUser,
                name = it.name,
                genres = it.genres,
                hours = it.hours,
                minutes = it.minutes,
                countries = it.countries,
                ratingKinopoisk = it.ratingKinopoisk,
                year = it.year,
                posterUrlPreview = it.posterUrlPreview,
                ratingKinopoiskVoteCount = it.ratingKinopoiskVoteCount
            )
        }
    }

    fun fromDomainModel(domainModel: FilmWatchedDomainModel): FilmWatchedDbModel {
        return FilmWatchedDbModel(
            idFilm = domainModel.idFilm,
            assessUser = domainModel.assessUser ?: 0,
            name = domainModel.name,
            genres = domainModel.genres,
            hours = domainModel.hours,
            minutes = domainModel.minutes,
            countries = domainModel.countries,
            ratingKinopoisk = domainModel.ratingKinopoisk,
            year = domainModel.year,
            posterUrlPreview = domainModel.posterUrlPreview,
            ratingKinopoiskVoteCount = domainModel.ratingKinopoiskVoteCount
        )
    }

}