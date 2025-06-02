package com.assistant.absolut.test.kinopoisk.presentation.film_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.assistant.absolut.test.kinopoisk.R
import com.assistant.absolut.test.kinopoisk.presentation.film_details.FilmDetailsIntent
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.ClickableTextColor
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.DarkText
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Padding
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Title1
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Title2
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Preview
@Composable
fun ContentFilmDetails(
    posterUrl: String = "",
    countries: ImmutableList<String> = persistentListOf(),
    year: Int = 0,
    genres: ImmutableList<String> = persistentListOf(),
    filmLength: Int = 0,
    hours: Int? = 0,
    minutes: Int? = 0,
    ratingAgeLimits: Int? = null,
    ratingKinopoisk: Float = 0.0.toFloat(),
    type: Int = 0,
    name: String = "",
    slogan: String = "",
    shortDescription: String = "",
    webUrl: String = "",
    intent: (FilmDetailsIntent) -> Unit = {}
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(Padding)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        TopContent(
            posterUrl = posterUrl,
            countries = countries,
            year = year,
            genres = genres,
            filmLength = filmLength,
            hours = hours,
            minutes = minutes,
            ratingAgeLimits = ratingAgeLimits,
            ratingKinopoisk = ratingKinopoisk,
            type = type,
        )

        Spacer(modifier = Modifier.size(10.dp))

        MiddleContent(
            name = name,
            slogan = slogan,
            shortDescription = shortDescription,
            webUrl = webUrl,
            intent)

    }
}

@Preview
@Composable
private fun TopContent(
    posterUrl: String = "",
    countries: ImmutableList<String> = persistentListOf(),
    year: Int = 0,
    genres: ImmutableList<String> = persistentListOf(),
    filmLength: Int = 0,
    hours: Int? = 0,
    minutes: Int? = 0,
    ratingAgeLimits: Int? = null,
    ratingKinopoisk: Float = 0.0.toFloat(),
    type: Int = 0
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {

        Image(
            painter = rememberAsyncImagePainter(posterUrl),
            contentDescription = "",
            modifier = Modifier
                .size(170.dp, 230.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(Padding),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                stringResource(id = R.string.countries, countries.joinToString()),
                fontSize = Title2,
                color = DarkText
            )

            Text(
                stringResource(id = R.string.year, year),
                fontSize = Title2,
                color = DarkText
            )

            Text(
                stringResource(id = R.string.genre, genres.joinToString()),
                fontSize = Title2,
                color = DarkText
            )


            Text(
                stringResource(id =
                R.string.film_length,
                    when(filmLength) {
                        R.string.filmLength_hours_minutes -> stringResource(id = R.string.filmLength_hours_minutes, hours!!, minutes!!)
                        R.string.filmLength_minutes -> stringResource(id = R.string.filmLength_minutes, minutes!!)
                        else -> stringResource(id = R.string.dash)
                    }
                ),
                fontSize = Title2,
                color = DarkText
            )

            Text(
                stringResource(
                    id = R.string.age,
                    when(ratingAgeLimits) {
                        null -> stringResource(id = R.string.dash)
                        else -> stringResource(id = R.string.plus, ratingAgeLimits)
                    }
                    ),
                fontSize = Title2,
                color = DarkText
            )

            Text(
                stringResource(id = R.string.rating, ratingKinopoisk),
                fontSize = Title2,
                color = DarkText
            )

            Text(
                stringResource(id = R.string.type, stringResource(id = type)),
                fontSize = Title2,
                color = DarkText
            )

        }

    }
}

@Preview
@Composable
private fun MiddleContent(
    name: String = "",
    slogan: String = "",
    shortDescription: String = "",
    webUrl: String = "",
    intent: (FilmDetailsIntent) -> Unit = {}
) {

    Text(
        name,
        fontSize = Title1,
        fontWeight = FontWeight.Bold,
        color = DarkText
    )

    Spacer(modifier = Modifier.size(10.dp))

    Text(
        stringResource(id = R.string.slogan, slogan),
        fontSize = Title2,
        color = DarkText
    )

    Spacer(modifier = Modifier.size(10.dp))

    Text(
        stringResource(id = R.string.short_description, shortDescription),
        fontSize = Title2,
        color = DarkText
    )


    Spacer(modifier = Modifier.size(10.dp))

    Text(
        stringResource(id = R.string.watch_on_kinopoisk),
        fontSize = Title2,
        color = ClickableTextColor,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier.clickable {
            intent(FilmDetailsIntent.GoToUrl(webUrl))
        }
    )
}