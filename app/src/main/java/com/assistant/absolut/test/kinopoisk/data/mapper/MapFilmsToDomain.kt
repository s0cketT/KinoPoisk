package com.assistant.absolut.test.kinopoisk.data.mapper

import com.assistant.absolut.test.kinopoisk.data.model.FilmsApiModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmsDomainModel
import com.assistant.absolut.test.kinopoisk.presentation.extensions.log


class MapFilmsToDomain {
    fun toDomainModel(apiModel: FilmsApiModel): FilmsDomainModel? = runCatching {
        FilmsDomainModel(
            totalPages = apiModel.totalPages?.toInt()!!,
            films = apiModel.items.mapNotNull { film ->
                runCatching {
                    FilmsDomainModel.Film(
                        id = film.kinopoiskId?.toInt()!!,
                        name = film.nameRu ?: film.nameOriginal!!,
                        countries = film.countries.mapNotNull { it.country },
                        ratingKinopoisk = film.ratingKinopoisk?.toFloat()!!,
                        year = film.year?.toInt()!!,
                        posterUrlPreview = film.posterUrlPreview!!,
                        assess = null,
                        isBookmark = false
                    )
                }.getOrElse {
                    log("FilmsDomainModel.Film is null")
                    null
                }
            }
        )
    }.getOrElse {
        log("FilmsDomainModel is null")
        null
    }
}