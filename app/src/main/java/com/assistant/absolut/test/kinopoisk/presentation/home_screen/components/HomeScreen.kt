package com.assistant.absolut.test.kinopoisk.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.assistant.absolut.test.kinopoisk.R
import com.assistant.absolut.test.kinopoisk.data.common.Constants
import com.assistant.absolut.test.kinopoisk.domain.model.FilmsDomainModel
import com.assistant.absolut.test.kinopoisk.presentation.components.CustomLoader
import com.assistant.absolut.test.kinopoisk.presentation.components.mockFilm
import com.assistant.absolut.test.kinopoisk.presentation.destinations.FilmDetailsScreenDestination

import com.assistant.absolut.test.kinopoisk.presentation.home_screen.HomeEvent
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.HomeIntent
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.HomeState
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.HomeViewModel
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.CardContentTextColor
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.DarkBackground
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.DarkError
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.DarkText
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Title3
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Title1
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import org.koin.androidx.compose.koinViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {

    val homeViewModel = koinViewModel<HomeViewModel>()
    val state by homeViewModel.state.collectAsStateWithLifecycle()
    val intent by remember { mutableStateOf(homeViewModel::processIntent) }
    val event: Flow<HomeEvent> by remember { mutableStateOf(homeViewModel.event) }

    LaunchedEffect(Unit) {
        event.filterIsInstance<HomeEvent.NavigateToFilmDetails>().collect { event ->
            navigator.navigate(FilmDetailsScreenDestination(filmId = event.filmId))
        }
    }

    UI(state = state, intent = intent)
}

@Preview
@Composable
private fun UI(
    state: HomeState = HomeState(),
    intent: (HomeIntent) -> Unit = {}
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        when {
            state.films.isNotEmpty() -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(state.films, key = { _, film -> film.id }) { index, film ->

                        FilmItem(film = film,
                            onFilmClick = { filmId ->
                                intent(HomeIntent.NavigateToFilmDetailsScreen(filmId))
                            })
                        HorizontalDivider()

                        if (index >= state.films.size - state.pageSize) {
                            intent(HomeIntent.LoadingNextPages)
                        }
                    }

                    if (state.isLoading) {
                        item {
                            CustomLoader()
                        }
                    }
                }

            }

            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CustomLoader()
                }
            }


            state.error != null -> {
                Text(
                    text = stringResource(id = state.error),
                    color = DarkError,
                    modifier = Modifier.padding(16.dp)
                )
            }

            else -> {
                Text(
                    text = stringResource(id = R.string.error_api),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun FilmItem(
    film: FilmsDomainModel.Film = mockFilm,
    onFilmClick: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onFilmClick(film.id) }
    ) {
        Box() {
            Image(
                painter = rememberAsyncImagePainter(film.posterUrlPreview),
                contentDescription = film.name,
                modifier = Modifier
                    .fillMaxSize()
                    .size(300.dp, 300.dp)
                    .align(Alignment.Center)
            )

            Box(
                modifier = Modifier
                    .background(
                        color = when {
                            film.ratingKinopoisk <= Constants.Three -> Color.Red
                            film.ratingKinopoisk <= Constants.Seven -> Color.Gray
                            else -> Color.Green
                        },
                        shape = RoundedCornerShape(8.dp)
                    )
                    .size(40.dp, 25.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Text(
                    text = "${film.ratingKinopoisk}",
                    color = CardContentTextColor,
                    fontSize = Title3,
                    modifier = Modifier.align(Alignment.Center),
                    fontWeight = FontWeight.ExtraBold,
                )
            }

        }

        Column {
            Text(
                text = film.name,
                color = DarkText,
                fontSize = Title1
            )
            Text(
                text = stringResource(id = R.string.year, film.year),
                color = CardContentTextColor,
                fontSize = Title3
            )

            Text(
                text = stringResource(id = R.string.countries, film.countries.joinToString()),
                color = CardContentTextColor,
                fontSize = Title3
            )

        }
    }
}