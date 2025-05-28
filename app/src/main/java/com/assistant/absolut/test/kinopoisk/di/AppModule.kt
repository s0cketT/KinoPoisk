package com.assistant.absolut.test.kinopoisk.di

import com.assistant.absolut.test.kinopoisk.data.mapper.MapFilmDetailsToDomain
import com.assistant.absolut.test.kinopoisk.data.mapper.MapFilmsToDomain
import com.assistant.absolut.test.kinopoisk.data.remote.IFilmDetailsApi
import com.assistant.absolut.test.kinopoisk.data.remote.IFilmsApi
import com.assistant.absolut.test.kinopoisk.data.repository.FilmDetailsRepositoryImpl
import com.assistant.absolut.test.kinopoisk.data.repository.FilmsRepositoryImpl
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmDetailsRepository
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmsRepository
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetFilmDetailsUseCase
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetFilmsUseCase
import com.assistant.absolut.test.kinopoisk.presentation.film_details.FilmDetailsViewModel
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { MapFilmsToDomain() }
    single<IFilmsRepository> {
        FilmsRepositoryImpl(
            filmsApi = get<IFilmsApi>(),
            mapper = get<MapFilmsToDomain>()
        )
    }
    factory<GetFilmsUseCase> { GetFilmsUseCase(filmsRepository = get<IFilmsRepository>()) }
    viewModel<HomeViewModel> { HomeViewModel(getFilmsUseCase = get<GetFilmsUseCase>()) }

    single { MapFilmDetailsToDomain() }
    single<IFilmDetailsRepository> {
        FilmDetailsRepositoryImpl(
            filmDetailsApi = get<IFilmDetailsApi>(),
            mapper = get<MapFilmDetailsToDomain>()
        )
    }
    factory<GetFilmDetailsUseCase> {
        GetFilmDetailsUseCase(filmDetailsRepository = get<IFilmDetailsRepository>())
    }
    viewModel<FilmDetailsViewModel> { (filmId: Int) ->
        FilmDetailsViewModel(
            getFilmDetailsUseCase = get<GetFilmDetailsUseCase>(),
            filmId = filmId
        )
    }

}