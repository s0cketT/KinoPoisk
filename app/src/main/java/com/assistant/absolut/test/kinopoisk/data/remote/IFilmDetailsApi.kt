package com.assistant.absolut.test.kinopoisk.data.remote

import com.assistant.absolut.test.kinopoisk.data.common.Constants
import com.assistant.absolut.test.kinopoisk.data.model.FilmDetailsApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface IFilmDetailsApi {
    @GET(Constants.FILM_DETAILS_API)
    suspend fun getFilmDetails(
        @Path("id") id: Int
    ): FilmDetailsApiModel
}