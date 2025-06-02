package com.assistant.absolut.test.kinopoisk.presentation.profile_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetAllFilmBookmarkUseCase
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetAllFilmWatchedUseCase
import com.assistant.absolut.test.kinopoisk.presentation.components.SingleFlowEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getAllFilmWatchedUseCase: GetAllFilmWatchedUseCase,
    private val getAllFilmBookmarkUseCase: GetAllFilmBookmarkUseCase
): ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    private val _event = SingleFlowEvent<ProfileEvent>(viewModelScope)
    val event = _event.flow

    init {
        getAllFilmWatched()
        getAllFilmBookmark()
    }

    fun processIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.SwitchFilterFilms -> {
                _state.update {
                    it.copy(filmsFilter = intent.filter)
                }
            }

            is ProfileIntent.NavigateToFilmDetailsScreen -> {
                val assess = state.value.filmsWatched.find { it.idFilm == intent.filmId }?.assessUser
                val isBookmark = state.value.filmsBookmark.any { it.idFilm == intent.filmId }

                _event.emit(
                    ProfileEvent.NavigateToFilmDetails(
                        filmId = intent.filmId,
                        assess = assess,
                        isBookmark = isBookmark
                    )
                )
            }
        }
    }

    private fun getAllFilmWatched() {
        viewModelScope.launch {
            getAllFilmWatchedUseCase().collect { watched ->
                _state.update {
                    it.copy(filmsWatched = watched)
                }
            }
        }
    }

    private fun getAllFilmBookmark() {
        viewModelScope.launch {
            getAllFilmBookmarkUseCase().collect { bookmark ->
                _state.update {
                    it.copy(
                        filmsBookmark = bookmark
                    )
                }
            }
        }
    }

}