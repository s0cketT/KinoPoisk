package com.assistant.absolut.test.kinopoisk.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assistant.absolut.test.kinopoisk.domain.common.Resource
import com.assistant.absolut.test.kinopoisk.domain.model.ExceptionDomainModel
import com.assistant.absolut.test.kinopoisk.domain.model.FilmsDomainModel
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetFilmsUseCase
import com.assistant.absolut.test.kinopoisk.presentation.parseToString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val getFilmsUseCase: GetFilmsUseCase): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getFilms(state.value.currentPage)
    }

    private fun getFilms(page: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isAppending = true) }
            when (
                val result: Resource<FilmsDomainModel, ExceptionDomainModel> =
                    getFilmsUseCase(page = page)
            ) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            films = it.films + result.data.films,
                            error = null,
                            maxPage = result.data.totalPages,
                            pageSize = result.data.films.size,
                            isAppending = false,
                            currentPage = it.currentPage + 1
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.exception.parseToString(),
                            isAppending = false
                            )
                    }
                }
            }
        }
    }

    fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadingNextPages -> {
                if (!state.value.isAppending && state.value.currentPage <= state.value.maxPage) {
                    getFilms(state.value.currentPage)
                }
            }
        }
    }

}