package com.assistant.absolut.test.kinopoisk.domain.model

data class FilmDetailsDomainModel(
    val name: String,
    val posterUrl: String,
    val ratingKinopoisk: Float,
    val webUrl: String,
    val year: Int,
    val filmLength: Int?,
    val slogan: String?,
    val shortDescription: String?,
    val type: FilmTypeDomainModel,
    val ratingAgeLimits: Int?,
    val countries: List<String>,
    val genres: List<String>
)
