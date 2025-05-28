package com.assistant.absolut.test.kinopoisk.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assistant.absolut.test.kinopoisk.domain.model.FilmsDomainModel
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetFilmsUseCase
import com.assistant.absolut.test.kinopoisk.presentation.components.SingleFlowEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val getFilmsUseCase: GetFilmsUseCase) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _event = SingleFlowEvent<HomeEvent>(viewModelScope)
    val event = _event.flow

    init {
        getFilms()
    }

    private fun getFilms() {
        _state.update {
            if (it.isLoading || it.isHaveNextPage.not()) return
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            var result: FilmsDomainModel? = null

            while (result == null) {
                result = getFilmsUseCase(page = state.value.nextPage).data
                if (result == null) {
                    delay(1000)
                }
            }

            _state.update {
                it.copy(
                    isLoading = false,
                    films = (it.films + result.films).distinctBy { idFilm -> idFilm.id },
                    error = null,
                    maxPage = result.totalPages,
                    pageSize = result.films.size,
                    nextPage = it.nextPage + 1
                )
            }
        }
    }

    fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadingNextPages -> {
                getFilms()
            }

            is HomeIntent.NavigateToFilmDetailsScreen -> {
                _event.emit(HomeEvent.NavigateToFilmDetails(intent.filmId))
            }
        }
    }

}