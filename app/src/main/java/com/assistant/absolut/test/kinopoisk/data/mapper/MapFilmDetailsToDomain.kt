package com.assistant.absolut.test.kinopoisk.data.mapper


import com.assistant.absolut.test.kinopoisk.data.model.FilmDetailsApiModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmDetailsDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmTypeDomainModel


class MapFilmDetailsToDomain {
    fun toDomainModel(apiModel: FilmDetailsApiModel): FilmDetailsDomainModel? = runCatching {
        FilmDetailsDomainModel(
            name = apiModel.nameRu ?: apiModel.nameOriginal!!,
            posterUrl = apiModel.posterUrlPreview!!,
            ratingKinopoisk = apiModel.ratingKinopoisk?.toFloat()!!,
            webUrl = apiModel.webUrl!!,
            year = apiModel.year?.toInt()!!,
            filmLength = apiModel.filmLength?.toInt(),
            slogan = apiModel.slogan,
            shortDescription = apiModel.shortDescription,
            type = apiModel.type?.let { type ->
                FilmTypeDomainModel.valueOf(type)
            }!!,
            ratingAgeLimits = apiModel.ratingAgeLimits?.removePrefix("age")?.toInt(),
            countries = apiModel.countries.mapNotNull { it.country },
            genres = apiModel.genres.mapNotNull { it.genre }
        )
    }.getOrElse {
        null
    }
}