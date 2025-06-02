package com.assistant.absolut.test.kinopoisk.di

import com.assistant.absolut.test.kinopoisk.data.dao.FilmBookmarkDao
import com.assistant.absolut.test.kinopoisk.data.dao.FilmWatchedDao
import com.assistant.absolut.test.kinopoisk.data.mapper.MapFilmBookmark
import com.assistant.absolut.test.kinopoisk.data.mapper.MapFilmDetailsToDomain
import com.assistant.absolut.test.kinopoisk.data.mapper.MapFilmWatched
import com.assistant.absolut.test.kinopoisk.data.mapper.MapFilmsToDomain
import com.assistant.absolut.test.kinopoisk.data.remote.IFilmDetailsApi
import com.assistant.absolut.test.kinopoisk.data.remote.IFilmsApi
import com.assistant.absolut.test.kinopoisk.data.repository.FilmBookmarkLocalRepositoryImpl
import com.assistant.absolut.test.kinopoisk.data.repository.FilmDetailsRemoteRepositoryImpl
import com.assistant.absolut.test.kinopoisk.data.repository.FilmWatchedLocalRepositoryImpl
import com.assistant.absolut.test.kinopoisk.data.repository.FilmsRepositoryRemoteImpl
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmBookmarkRepository
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmDetailsRepository
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmWatchedRepository
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmsRepository
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetAllFilmBookmarkUseCase
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetAllFilmWatchedUseCase
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetFilmDetailsUseCase
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetFilmsUseCase
import com.assistant.absolut.test.kinopoisk.domain.use_case.InsertOrDeleteFilmBookmarkUseCase
import com.assistant.absolut.test.kinopoisk.domain.use_case.InsertOrDeleteFilmWatchedUseCase
import com.assistant.absolut.test.kinopoisk.presentation.film_details.FilmDetailsViewModel
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.HomeViewModel
import com.assistant.absolut.test.kinopoisk.presentation.profile_screen.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single<MapFilmsToDomain> { MapFilmsToDomain() }
    single<IFilmsRepository> {
        FilmsRepositoryRemoteImpl(
            filmsApi = get<IFilmsApi>(),
            mapper = get<MapFilmsToDomain>()
        )
    }
    factory<GetFilmsUseCase> { GetFilmsUseCase(
        filmsRepository = get<IFilmsRepository>(),
        getAllFilmWatchedUseCase = get<IFilmWatchedRepository>(),
        getAllFilmBookmarkUseCase = get<IFilmBookmarkRepository>()
        ) }
    viewModel<HomeViewModel> { HomeViewModel(
        getFilmsUseCase = get<GetFilmsUseCase>(),
        getAllFilmWatchedUseCase = get<GetAllFilmWatchedUseCase>(),
        getAllFilmBookmarkUseCase = get<GetAllFilmBookmarkUseCase>()
        ) }


    single<MapFilmDetailsToDomain> { MapFilmDetailsToDomain() }
    single<IFilmDetailsRepository> {
        FilmDetailsRemoteRepositoryImpl(
            filmDetailsApi = get<IFilmDetailsApi>(),
            mapper = get<MapFilmDetailsToDomain>()
        )
    }
    factory<GetFilmDetailsUseCase> {
        GetFilmDetailsUseCase(filmDetailsRepository = get<IFilmDetailsRepository>())
    }
    viewModel<FilmDetailsViewModel> { (filmId: Int, assess: Int?, isBookmark: Boolean) ->
        FilmDetailsViewModel(
            getFilmDetailsUseCase = get<GetFilmDetailsUseCase>(),
            filmId = filmId,
            assess = assess,
            isBookmark = isBookmark,
            insertOrDeleteFilmWatchedUseCase = get<InsertOrDeleteFilmWatchedUseCase>(),
            insertOrDeleteFilmBookmarkUseCase = get<InsertOrDeleteFilmBookmarkUseCase>()
        )
    }


    single<MapFilmWatched> { MapFilmWatched() }
    single<IFilmWatchedRepository> {
        FilmWatchedLocalRepositoryImpl(
            dao = get<FilmWatchedDao>(),
            mapper = get<MapFilmWatched>()
        )
    }
    factory<GetAllFilmWatchedUseCase> {
        GetAllFilmWatchedUseCase(
            filmWatchedRepository = get<IFilmWatchedRepository>()
        )
    }
    factory<InsertOrDeleteFilmWatchedUseCase> {
        InsertOrDeleteFilmWatchedUseCase(
            filmWatchedRepository = get<IFilmWatchedRepository>()
        )
    }
    single<MapFilmBookmark> { MapFilmBookmark() }
    single<IFilmBookmarkRepository> {
        FilmBookmarkLocalRepositoryImpl(
            dao = get<FilmBookmarkDao>(),
            mapper = get<MapFilmBookmark>()
        )
    }
    factory<GetAllFilmBookmarkUseCase> {
        GetAllFilmBookmarkUseCase(
            filmBookmarkRepository = get<IFilmBookmarkRepository>()
        )
    }
    factory<InsertOrDeleteFilmBookmarkUseCase> {
        InsertOrDeleteFilmBookmarkUseCase(
            filmBookmarkRepository = get<IFilmBookmarkRepository>()
        )
    }

    viewModel<ProfileViewModel> {
        ProfileViewModel(
            getAllFilmWatchedUseCase = get<GetAllFilmWatchedUseCase>(),
            getAllFilmBookmarkUseCase = get<GetAllFilmBookmarkUseCase>()
        )
    }
}