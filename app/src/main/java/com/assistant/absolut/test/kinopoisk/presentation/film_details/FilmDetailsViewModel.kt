package com.assistant.absolut.test.kinopoisk.presentation.film_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assistant.absolut.test.kinopoisk.domain.common.Resource
import com.assistant.absolut.test.kinopoisk.domain.model.ExceptionDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmBookmarkDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmDetailsDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmWatchedDomainModel
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetFilmDetailsUseCase
import com.assistant.absolut.test.kinopoisk.domain.use_case.InsertOrDeleteFilmBookmarkUseCase
import com.assistant.absolut.test.kinopoisk.domain.use_case.InsertOrDeleteFilmWatchedUseCase
import com.assistant.absolut.test.kinopoisk.presentation.components.SingleFlowEvent
import com.assistant.absolut.test.kinopoisk.presentation.extensions.filmTypeStringToUi
import com.assistant.absolut.test.kinopoisk.presentation.extensions.parseToString
import com.assistant.absolut.test.kinopoisk.presentation.extensions.toFilmLengthUi
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class FilmDetailsViewModel(
    private val getFilmDetailsUseCase: GetFilmDetailsUseCase,
    private val filmId: Int,
    private val assess: Int?,
    private val isBookmark: Boolean,
    private val insertOrDeleteFilmWatchedUseCase: InsertOrDeleteFilmWatchedUseCase,
    private val insertOrDeleteFilmBookmarkUseCase: InsertOrDeleteFilmBookmarkUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FilmDetailsState(
        id = filmId,
        assess = assess,
        isBookmark = isBookmark)
    )
    val state = _state.asStateFlow()

    private val _event = SingleFlowEvent<FilmDetailsEvent>(viewModelScope)
    val event = _event.flow

    init {
        getFilmDetails()
    }

    private fun getFilmDetails() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            when (
                val result: Resource<FilmDetailsDomainModel, ExceptionDomainModel> =
                    getFilmDetailsUseCase(filmId = state.value.id)
            ) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            name = result.data.name,
                            posterUrl = result.data.posterUrl,
                            ratingKinopoisk = result.data.ratingKinopoisk,
                            ratingAgeLimits = result.data.ratingAgeLimits,
                            webUrl = result.data.webUrl,
                            year = result.data.year,
                            filmLength = result.data.filmLength.toFilmLengthUi(),
                            hours = result.data.filmLength?.div(60),
                            minutes = result.data.filmLength?.mod(60),
                            slogan = result.data.slogan ?: "—",
                            shortDescription = result.data.shortDescription ?: "—",
                            type = result.data.type.filmTypeStringToUi(),
                            countries = persistentListOf<String>().addAll(result.data.countries),
                            genres = persistentListOf<String>().addAll(result.data.genres),
                            error = null,
                            ratingKinopoiskVoteCount = result.data.ratingKinopoiskVoteCount
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.exception.parseToString()
                        )
                    }
                }
            }
        }
    }

    fun processIntent(intent: FilmDetailsIntent) {
        when (intent) {
            is FilmDetailsIntent.GoToUrl -> {
                _event.emit(FilmDetailsEvent.GoToUrl(intent.url))
            }

            is FilmDetailsIntent.ShowDialog -> {
                _state.update {
                    it.copy(isDialog = !it.isDialog)
                }
            }

            is FilmDetailsIntent.AddOrRemoveBookmark -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(isBookmark = !it.isBookmark)
                    }

                    insertOrDeleteFilmBookmarkUseCase(
                        filmBookmark = FilmBookmarkDomainModel(
                            idFilm = state.value.id,
                            name = state.value.name,
                            genres = state.value.genres,
                            hours = state.value.hours,
                            minutes = state.value.minutes,
                            countries = state.value.countries,
                            ratingKinopoisk = state.value.ratingKinopoisk,
                            year = state.value.year,
                            posterUrlPreview = state.value.posterUrl
                        ),
                        isBookmark = state.value.isBookmark
                    )
                }
            }

            is FilmDetailsIntent.EnterTempAssess -> {
                val number = intent.tempAssess.toIntOrNull()
                if (intent.tempAssess.isEmpty() || (number in 1..10)) {
                    _state.update {
                        it.copy(
                            tempAssess = intent.tempAssess
                        )
                    }
                }
            }

            is FilmDetailsIntent.SaveAssess -> {
                viewModelScope.launch {
                    _state.update {
                        if (it.tempAssess.isEmpty()) {
                            it.copy(
                                assess = null,
                                isDialog = !it.isDialog
                            )
                        } else {
                            it.copy(
                                assess = it.tempAssess.toInt(),
                                isDialog = !it.isDialog
                            )
                        }
                    }
                    insertOrDeleteFilmWatchedUseCase(filmWatched = FilmWatchedDomainModel(
                        idFilm = state.value.id,
                        assessUser = state.value.assess,
                        name = state.value.name,
                        genres = state.value.genres,
                        hours = state.value.hours,
                        minutes = state.value.minutes,
                        countries = state.value.countries,
                        ratingKinopoisk = state.value.ratingKinopoisk,
                        year = state.value.year,
                        posterUrlPreview = state.value.posterUrl,
                        ratingKinopoiskVoteCount = state.value.ratingKinopoiskVoteCount
                    ))
                }
            }

            FilmDetailsIntent.NavigateBack -> {
                _event.emit(FilmDetailsEvent.NavigateBack)
            }
        }
    }
}


