package com.assistant.absolut.test.kinopoisk.presentation.home_screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assistant.absolut.test.kinopoisk.domain.model.FilmsDomainModel
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetAllFilmBookmarkUseCase
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetAllFilmWatchedUseCase
import com.assistant.absolut.test.kinopoisk.domain.use_case.GetFilmsUseCase
import com.assistant.absolut.test.kinopoisk.presentation.components.SingleFlowEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getFilmsUseCase: GetFilmsUseCase,
    private val getAllFilmWatchedUseCase: GetAllFilmWatchedUseCase,
    private val getAllFilmBookmarkUseCase: GetAllFilmBookmarkUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _event = SingleFlowEvent<HomeEvent>(viewModelScope)
    val event = _event.flow

    init {
        getFilms()
        getAllFilmWatched()
        getAllFilmBookmark()
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
                _event.emit(
                    HomeEvent.NavigateToFilmDetails(
                        filmId = intent.filmId,
                        assess = intent.assess,
                        isBookmark = intent.isBookmark
                    )
                )
            }
        }
    }

    private fun getAllFilmWatched() {
        viewModelScope.launch {
            getAllFilmWatchedUseCase().collect { watched ->
                _state.update {
                    val updatedFilms = it.films.map { film ->
                        val filmWatched = watched.find { it.idFilm == film.id }
                        film.copy(assess = filmWatched?.assessUser)
                    }
                    it.copy(films = updatedFilms)
                }
            }
        }
    }

    private fun getAllFilmBookmark() {
        viewModelScope.launch {
            getAllFilmBookmarkUseCase().collect { bookmark ->
                _state.update {
                    val updatedPosts = it.films.map { post ->
                        post.copy(isBookmark = bookmark.any { it.idFilm == post.id })
                    }

                    it.copy(films = updatedPosts)
                }
            }
        }
    }

}