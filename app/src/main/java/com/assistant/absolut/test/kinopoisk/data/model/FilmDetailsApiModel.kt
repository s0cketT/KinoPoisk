package com.assistant.absolut.test.kinopoisk.data.model


data class FilmDetailsApiModel(
    val nameRu: String?,
    val nameOriginal: String?,
    val posterUrlPreview: String?,
    val posterUrl: String?,
    val ratingKinopoisk: String?,
    val webUrl: String?,
    val year: String?,
    val filmLength: String?,
    val slogan: String?,
    val shortDescription: String?,
    val type: String?,
    val ratingAgeLimits: String?,
    val countries: List<Country>,
    val genres: List<Genre>,
    val ratingKinopoiskVoteCount: String?
) {
    data class Country(
        val country: String?
    )

    data class Genre(
        val genre: String?
    )
}
