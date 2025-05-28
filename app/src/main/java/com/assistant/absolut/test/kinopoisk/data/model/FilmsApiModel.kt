package com.assistant.absolut.test.kinopoisk.data.model


data class FilmsApiModel(
    val totalPages: String?,
    val items: List<FilmApiModel>
) {
    data class FilmApiModel(
        val kinopoiskId: String?,
        val nameRu: String?,
        val nameOriginal: String?,
        val countries: List<CountryApiModel>,
        val ratingKinopoisk: String?,
        val year: String?,
        val posterUrlPreview: String?
    )

    data class CountryApiModel(
        val country: String?
    )
}




