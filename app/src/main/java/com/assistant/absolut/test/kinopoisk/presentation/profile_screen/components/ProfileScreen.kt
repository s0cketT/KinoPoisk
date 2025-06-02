package com.assistant.absolut.test.kinopoisk.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assistant.absolut.test.kinopoisk.R
import com.assistant.absolut.test.kinopoisk.presentation.destinations.FilmDetailsScreenDestination
import com.assistant.absolut.test.kinopoisk.presentation.profile_screen.FilmsFilter
import com.assistant.absolut.test.kinopoisk.presentation.profile_screen.ProfileEvent
import com.assistant.absolut.test.kinopoisk.presentation.profile_screen.ProfileIntent
import com.assistant.absolut.test.kinopoisk.presentation.profile_screen.ProfileState
import com.assistant.absolut.test.kinopoisk.presentation.profile_screen.ProfileViewModel
import com.assistant.absolut.test.kinopoisk.presentation.profile_screen.components.ItemFilmBookmark
import com.assistant.absolut.test.kinopoisk.presentation.profile_screen.components.ItemFilmWatched
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.BootonColorActive
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.BootonColorInactive
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.DarkBackground
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.DarkText
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Padding
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Title2
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import org.koin.androidx.compose.koinViewModel

@RootNavGraph
@Destination
@Composable
fun ProfileScreen(navigator: DestinationsNavigator) {

    val profileViewModel = koinViewModel<ProfileViewModel>()
    val state by profileViewModel.state.collectAsStateWithLifecycle()
    val intent by remember { mutableStateOf(profileViewModel::processIntent) }
    val event: Flow<ProfileEvent> by remember { mutableStateOf(profileViewModel.event) }

    LaunchedEffect(Unit) {
        event.filterIsInstance<ProfileEvent.NavigateToFilmDetails>().collect { event ->
            navigator.navigate(
                FilmDetailsScreenDestination(
                    filmId = event.filmId,
                    assess = event.assess,
                    isBookmark = event.isBookmark
                )
            )
        }
    }

    UI(state = state, intent = intent)
}


@Preview
@Composable
private fun UI(
    state: ProfileState = ProfileState(),
    intent: (ProfileIntent) -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(Padding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                { intent(ProfileIntent.SwitchFilterFilms(filter = FilmsFilter.WATCHED)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                    when (state.filmsFilter) {
                        FilmsFilter.WATCHED -> BootonColorActive
                        else -> BootonColorInactive
                    }
                )
            ) {
                Text(
                    stringResource(id = R.string.film_watched),
                    color = DarkText,
                    fontSize = Title2
                )
            }

            Button(
                { intent(ProfileIntent.SwitchFilterFilms(filter = FilmsFilter.BOOKMARKS)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                    when (state.filmsFilter) {
                        FilmsFilter.BOOKMARKS -> BootonColorActive
                        else -> BootonColorInactive
                    }
                )
            ) {
                Text(
                    stringResource(id = R.string.film_bookmark),
                    color = DarkText,
                    fontSize = Title2
                )
            }
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            when (state.filmsFilter) {
                FilmsFilter.WATCHED -> {
                    itemsIndexed(state.filmsWatched, key = { _, film -> film.idFilm }) { _, film ->
                        ItemFilmWatched(
                            filmWatched = film,
                            onFilmClick = { filmId ->
                                intent(
                                    ProfileIntent.NavigateToFilmDetailsScreen(
                                        filmId = filmId
                                    )
                                )
                            }
                        )
                    }
                }

                FilmsFilter.BOOKMARKS -> {
                    itemsIndexed(state.filmsBookmark, key = { _, film -> film.idFilm }) { _, film ->
                        ItemFilmBookmark(
                            filmBookmark = film,
                            onFilmClick = { filmId ->
                                intent(
                                    ProfileIntent.NavigateToFilmDetailsScreen(
                                        filmId = filmId
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}




