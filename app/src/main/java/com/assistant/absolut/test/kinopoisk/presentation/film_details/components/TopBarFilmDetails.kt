package com.assistant.absolut.test.kinopoisk.presentation.film_details.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assistant.absolut.test.kinopoisk.R
import com.assistant.absolut.test.kinopoisk.data.common.Constants
import com.assistant.absolut.test.kinopoisk.presentation.film_details.FilmDetailsIntent
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.BookmarkFav
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Padding
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.SizeImg
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.TextColor
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Title2

@Preview
@Composable
fun TopBarFilmDetails(
    assess: String? = null,
    isBookmark: Boolean = false,
    intent: (FilmDetailsIntent) -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Padding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.back
            ),
            contentDescription = null,
            modifier = Modifier
                .size(SizeImg)
                .clickable { intent(FilmDetailsIntent.NavigateBack) },
            colorFilter = ColorFilter.tint(Color.Gray)
        )

        if (!assess.isNullOrEmpty()) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(
                            color = when {
                                assess.toInt() <= Constants.Three -> Color.Red
                                assess.toInt() <= Constants.Seven -> Color.Gray
                                else -> Color.Green
                            },
                            shape = CircleShape
                        )
                        .clickable { intent(FilmDetailsIntent.ShowDialog) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        assess,
                        fontSize = Title2,
                        color = TextColor,
                    )
                }

                Text(
                    stringResource(id = R.string.my_assess),
                    fontSize = Title2,
                    color = TextColor,
                )
            }
        } else {
            Image(
                painter = painterResource(
                    id = R.drawable.assess
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(SizeImg)
                    .clickable { intent(FilmDetailsIntent.ShowDialog) },
                colorFilter = ColorFilter.tint(Color.Gray)
            )
        }
        Image(
            painter = painterResource(
                id = R.drawable.bookmark
            ),
            contentDescription = null,
            modifier = Modifier
                .size(SizeImg)
                .clickable { intent(FilmDetailsIntent.AddOrRemoveBookmark) },
            colorFilter = ColorFilter.tint(if (!isBookmark) Color.Gray else BookmarkFav)
        )
    }
}