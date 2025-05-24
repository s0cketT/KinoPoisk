package com.assistant.absolut.test.kinopoisk.di

import com.assistant.absolut.test.kinopoisk.data.remote.IFilmsApi
import com.assistant.absolut.test.kinopoisk.data.repository.FilmsRepositoryImpl
import com.assistant.absolut.test.kinopoisk.domain.repository.IFilmsRepository
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetFilmsUseCase
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<IFilmsRepository> { FilmsRepositoryImpl(filmsApi = get<IFilmsApi>()) }
    factory<GetFilmsUseCase> { GetFilmsUseCase(filmsRepository = get<IFilmsRepository>()) }
    viewModel<HomeViewModel> { HomeViewModel(getFilmsUseCase = get<GetFilmsUseCase>()) }

}