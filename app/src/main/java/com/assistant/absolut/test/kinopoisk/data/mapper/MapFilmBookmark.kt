package com.assistant.absolut.test.kinopoisk.data.mapper


import com.assistant.absolut.test.kinopoisk.data.model.FilmBookmarkDbModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmBookmarkDomainModel


class MapFilmBookmark {
    fun toDomainModel(dbModel: List<FilmBookmarkDbModel>): List<FilmBookmarkDomainModel> {
        return dbModel.map {
            FilmBookmarkDomainModel(
                idFilm = it.idFilm,
                name = it.name,
                genres = it.genres,
                hours = it.hours,
                minutes = it.minutes,
                countries = it.countries,
                ratingKinopoisk = it.ratingKinopoisk,
                year = it.year,
                posterUrlPreview = it.posterUrlPreview
            )
        }
    }

    fun fromDomainModel(domainModel: FilmBookmarkDomainModel): FilmBookmarkDbModel {
        return FilmBookmarkDbModel(
            idFilm = domainModel.idFilm,
            name = domainModel.name,
            genres = domainModel.genres,
            hours = domainModel.hours,
            minutes = domainModel.minutes,
            countries = domainModel.countries,
            ratingKinopoisk = domainModel.ratingKinopoisk,
            year = domainModel.year,
            posterUrlPreview = domainModel.posterUrlPreview,
        )
    }
}
