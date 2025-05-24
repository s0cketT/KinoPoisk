package com.assistant.absolut.test.kinopoisk.data.remote

import com.assistant.absolut.test.kinopoisk.data.common.Constants
import com.assistant.absolut.test.kinopoisk.data.model.FilmsApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface IFilmsApi {
    @GET(Constants.FILMS_API)
    suspend fun getFilms(
        @Query(Constants.PAGE) page: Int
    ): FilmsApiModel
}