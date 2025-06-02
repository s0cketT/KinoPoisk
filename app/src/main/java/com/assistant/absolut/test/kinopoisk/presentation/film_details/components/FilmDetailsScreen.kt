package com.assistant.absolut.test.kinopoisk.presentation.film_details.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assistant.absolut.test.kinopoisk.presentation.components.CustomLoader
import com.assistant.absolut.test.kinopoisk.presentation.film_details.FilmDetailsEvent
import com.assistant.absolut.test.kinopoisk.presentation.film_details.FilmDetailsIntent
import com.assistant.absolut.test.kinopoisk.presentation.film_details.FilmDetailsState
import com.assistant.absolut.test.kinopoisk.presentation.film_details.FilmDetailsViewModel
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.DarkBackground
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.DarkError
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@RootNavGraph
@Destination
@Composable
fun FilmDetailsScreen(
    navigator: DestinationsNavigator,
    filmId: Int,
    assess: Int?,
    isBookmark: Boolean
) {

    val filmDetailsViewModel =
        koinViewModel<FilmDetailsViewModel>(parameters = { parametersOf(filmId, assess, isBookmark) })
    val state by filmDetailsViewModel.state.collectAsStateWithLifecycle()
    val intent by remember { mutableStateOf(filmDetailsViewModel::processIntent) }
    val event: Flow<FilmDetailsEvent> by remember { mutableStateOf(filmDetailsViewModel.event) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        event.filterIsInstance<FilmDetailsEvent>().collect { event ->
            when(event) {
                is FilmDetailsEvent.GoToUrl -> context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(event.url)))
                is FilmDetailsEvent.NavigateBack -> { navigator.navigateUp() }
            }

        }
    }

    UI(state = state, intent = intent)

}

@Preview
@Composable
private fun UI(
    state: FilmDetailsState = FilmDetailsState(id = 0, assess = null, isBookmark = false),
    intent: (FilmDetailsIntent) -> Unit = {}
) {

    if (state.isDialog) {
        Dialog(
            onDismissRequest = { intent(FilmDetailsIntent.ShowDialog) },
        ) {
            DialogUiFilmDetails(
                posterUrl = state.posterUrl,
                name = state.name,
                year = state.year,
                tempAssess = state.tempAssess,
                assess = state.assess,
                intent = intent
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarFilmDetails(
            assess = state.assess,
            isBookmark = state.isBookmark,
            intent = intent)

        when {
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
                ContentFilmDetails(
                    posterUrl = state.posterUrl,
                    countries = state.countries,
                    year = state.year,
                    genres = state.genres,
                    filmLength = state.filmLength,
                    hours = state.hours,
                    minutes = state.minutes,
                    ratingKinopoisk = state.ratingKinopoisk,
                    ratingAgeLimits = state.ratingAgeLimits,
                    type = state.type,
                    name = state.name,
                    slogan = state.slogan,
                    shortDescription = state.shortDescription,
                    webUrl = state.webUrl,
                    intent = intent)
            }
        }
    }
}



