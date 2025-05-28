package com.assistant.absolut.test.kinopoisk.di

import com.assistant.absolut.test.kinopoisk.data.common.NetworkFactory
import com.assistant.absolut.test.kinopoisk.data.remote.IFilmDetailsApi
import com.assistant.absolut.test.kinopoisk.data.remote.IFilmsApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single<OkHttpClient> { NetworkFactory.createOkHttpClient() }
    single<Retrofit> { NetworkFactory.createRetrofit(get<OkHttpClient>()) }

    single<IFilmsApi> { get<Retrofit>().create(IFilmsApi::class.java) }
    single<IFilmDetailsApi> { get<Retrofit>().create(IFilmDetailsApi::class.java) }
}