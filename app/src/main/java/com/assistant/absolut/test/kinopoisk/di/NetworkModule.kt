package com.assistant.absolut.test.kinopoisk.di

import com.assistant.absolut.test.kinopoisk.data.common.Constants
import com.assistant.absolut.test.kinopoisk.data.remote.IFilmsApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .header(Constants.API_KEY_HEADER, Constants.API_KEY)
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<IFilmsApi> { get<Retrofit>().create(IFilmsApi::class.java) }
}