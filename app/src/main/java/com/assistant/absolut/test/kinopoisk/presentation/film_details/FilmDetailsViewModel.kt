package com.assistant.absolut.test.kinopoisk.presentation.film_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assistant.absolut.test.kinopoisk.domain.common.Resource
import com.assistant.absolut.test.kinopoisk.domain.model.ExceptionDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmDetailsDomainModel
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetFilmDetailsUseCase
import com.assistant.absolut.test.kinopoisk.presentation.extensions.parseToString
import com.assistant.absolut.test.kinopoisk.presentation.extensions.toFilmLengthUi
import com.assistant.absolut.test.kinopoisk.presentation.extensions.toRatingAgeLimitsUi
import com.assistant.absolut.test.kinopoisk.presentation.components.SingleFlowEvent
import com.assistant.absolut.test.kinopoisk.presentation.extensions.filmTypeToUi
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class FilmDetailsViewModel(
    private val getFilmDetailsUseCase: GetFilmDetailsUseCase,
    private val filmId: Int,
) : ViewModel() {

    private val _state = MutableStateFlow(FilmDetailsState(id = filmId))
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
                            ratingAgeLimits = result.data.ratingAgeLimits.toRatingAgeLimitsUi(),
                            webUrl = result.data.webUrl,
                            year = result.data.year,
                            filmLength = result.data.filmLength.toFilmLengthUi(),
                            slogan = result.data.slogan ?: "—",
                            shortDescription = result.data.shortDescription ?: "—",
                            type = result.data.type.filmTypeToUi(),
                            countries = persistentListOf<String>().addAll(result.data.countries),
                            genres = persistentListOf<String>().addAll(result.data.genres),
                            error = null
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
                _state.update {
                    it.copy(isBookmark = !it.isBookmark)
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
                _state.update {
                    it.copy(
                        assess = it.tempAssess,
                        isDialog = !it.isDialog
                        )
                }
            }

            FilmDetailsIntent.NavigateBack -> {
                _event.emit(FilmDetailsEvent.NavigateBack)
            }
        }
    }
}


