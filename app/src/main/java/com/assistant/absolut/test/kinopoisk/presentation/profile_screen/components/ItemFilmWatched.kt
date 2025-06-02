package com.assistant.absolut.test.kinopoisk.presentation.profile_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.assistant.absolut.test.kinopoisk.R
import com.assistant.absolut.test.kinopoisk.data.common.Constants
import com.assistant.absolut.test.kinopoisk.domain.model.FilmWatchedDomainModel
import com.assistant.absolut.test.kinopoisk.presentation.components.mockFilmWatched
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.ColorAssessBad
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.ColorAssessGood
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.ColorAssessNormal
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.DarkText
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.DarkTextGray
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Padding
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.PaddingItem
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.SizeCircleAssessProfile
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Title1
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Title2
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Title3

@Preview
@Composable
fun ItemFilmWatched(
    filmWatched: FilmWatchedDomainModel = mockFilmWatched,
    onFilmClick: (Int) -> Unit = {}
    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingItem)
            .clickable { onFilmClick(filmWatched.idFilm) },
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = rememberAsyncImagePainter(filmWatched.posterUrlPreview),
            contentDescription = "",
            modifier = Modifier
                .size(100.dp, 130.dp)
        )

        ColumnItem(
            name = filmWatched.name,
            year = filmWatched.year,
            genres = filmWatched.genres,
            ratingKinopoisk = filmWatched.ratingKinopoisk,
            ratingKinopoiskVoteCount = filmWatched.ratingKinopoiskVoteCount,
            assessUser = filmWatched.assessUser!!,
            countries = filmWatched.countries,
            hours = filmWatched.hours,
            minutes = filmWatched.minutes
        )
    }
}

@Preview
@Composable
private fun ColumnItem(
    name: String = "",
    year: Int = 0,
    genres: List<String> = emptyList(),
    ratingKinopoisk: Float = 0.toFloat(),
    ratingKinopoiskVoteCount: Int = 0,
    assessUser: Int = 0,
    countries: List<String> = emptyList(),
    hours: Int? = 0,
    minutes: Int? = 0
) {
    Column(modifier = Modifier.padding(Padding)) {
        Text(
            name,
            color = DarkText,
            fontSize = Title1
        )

        Text(
            "(${year})",
            color = DarkText,
            fontSize = Title2
        )

        Text(
            genres.joinToString(),
            color = DarkTextGray,
            fontSize = Title2
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                "$ratingKinopoisk",
                color = when {
                    ratingKinopoisk <= Constants.FILM_SCORE_THREE -> ColorAssessBad
                    ratingKinopoisk <= Constants.FILM_SCORE_SEVEN -> ColorAssessNormal
                    else -> ColorAssessGood
                },
                fontSize = Title3
            )

            Text(
                "$ratingKinopoiskVoteCount",
                color = DarkTextGray,
                fontSize = Title3
            )

            Box(
                modifier = Modifier
                    .size(SizeCircleAssessProfile)
                    .background(
                        color = when {
                            assessUser <= Constants.FILM_SCORE_THREE -> ColorAssessBad
                            assessUser <= Constants.FILM_SCORE_SEVEN -> ColorAssessNormal
                            else -> ColorAssessGood
                        },
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "$assessUser",
                    fontSize = Title3,
                    color = DarkText,
                )
            }


            Text(
                countries.joinToString() + " " +
                        when {
                            hours != null -> stringResource(
                                id = R.string.film_length_watched_hours_minutes,
                                hours,
                                minutes!!
                            )

                            minutes != null -> stringResource(
                                id = R.string.film_length_watched_minutes,
                                minutes
                            )

                            else -> stringResource(id = R.string.dash)
                        },
                color = DarkText,
                fontSize = Title3
            )
        }
    }
}