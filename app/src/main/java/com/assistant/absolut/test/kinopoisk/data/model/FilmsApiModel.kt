package com.assistant.absolut.test.kinopoisk.data.model


import com.assistant.absolut.test.kinopoisk.domain.model.FilmsDomainModel


data class FilmsApiModel(
    val totalPages: String?,
    val items: List<Film>
) {
    fun toDomainModel(): FilmsDomainModel? = runCatching {
        FilmsDomainModel(
            totalPages = totalPages?.toInt()!!,
            films = items.mapNotNull { film ->
                runCatching {
                    FilmsDomainModel.Film(
                        id = film.kinopoiskId?.toInt()!!,
                        name = film.nameRu ?: film.nameOriginal!!,
                        countries = film.countries.mapNotNull { it.country },
                        ratingKinopoisk = film.ratingKinopoisk?.toFloat()!!,
                        year = film.year?.toInt()!!,
                        posterUrlPreview = film.posterUrlPreview!!
                    )
                }.getOrElse {
                    null
                }
            }
        )
    }.getOrElse {
        null
    }

}

data class Film(
    val kinopoiskId: String?,
    val nameRu: String?,
    val nameOriginal: String?,
    val countries: List<Country>,
    val ratingKinopoisk: String?,
    val year: String?,
    val posterUrlPreview: String?
)

data class Country(
    val country: String?
)

