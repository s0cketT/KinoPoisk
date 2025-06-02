package com.assistant.absolut.test.kinopoisk.domain.model


data class FilmsDomainModel(
    val totalPages: Int,
    val films: List<Film>
) {
    data class Film(
        val id: Int,
        val name: String,
        val countries: List<String>,
        val ratingKinopoisk: Float,
        val year: Int,
        val posterUrlPreview: String,

        val assess: Int?,
        val isBookmark: Boolean
    )
}
