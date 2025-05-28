package com.assistant.absolut.test.kinopoisk.presentation.film_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.assistant.absolut.test.kinopoisk.R
import com.assistant.absolut.test.kinopoisk.presentation.film_details.FilmDetailsIntent
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.ButtonColorAssess
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.ButtonColorChangeAssess
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.ButtonColorDeleteAssess
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.CardBackgroundColor
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Padding
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.SizeImg
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.TextColor
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Title1
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.Title2

@Preview
@Composable
fun DialogUiFilmDetails(
    posterUrl: String = "",
    name: String = "",
    year: Int = 0,
    tempAssess: String = "",
    assess: String? = null,
    intent: (FilmDetailsIntent) -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    stringResource(id = R.string.assess),
                    fontSize = Title1,
                    color = TextColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )

                Image(
                    painter = painterResource(R.drawable.close),
                    contentDescription = "",
                    modifier = Modifier
                        .size(SizeImg)
                        .align(Alignment.CenterEnd)
                        .clickable { intent(FilmDetailsIntent.ShowDialog) },
                    colorFilter = ColorFilter.tint(Color.Gray)
                )
            }

            Spacer(modifier = Modifier.size(10.dp))

            Image(
                painter = rememberAsyncImagePainter(posterUrl),
                contentDescription = "",
                modifier = Modifier
                    .size(150.dp)
            )

            Text(
                name,
                fontSize = Title1,
                color = TextColor,
                fontWeight = FontWeight.Bold
            )

            Text(
                "${year}",
                fontSize = Title2,
                color = TextColor,
            )


            Spacer(modifier = Modifier.size(16.dp))

            TextFieldAssessFilmDetails(
                text = tempAssess,
                onTextChange = { intent(FilmDetailsIntent.EnterTempAssess(it)) },
                hint = stringResource(id = R.string.assess_write),
                modifier = Modifier.padding(Padding)
            )

            Button(
                onClick = {
                    intent(FilmDetailsIntent.SaveAssess)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                    when {
                        assess != null && tempAssess.isEmpty() -> ButtonColorDeleteAssess
                        assess == null -> ButtonColorAssess
                        else -> ButtonColorChangeAssess
                    },
                    )
            ) {
                Text(
                    when {
                        assess != null && tempAssess.isEmpty() -> stringResource(id = R.string.delete_assess)
                        assess == null -> stringResource(id = R.string.assess)
                        else -> stringResource(id = R.string.change_assess)
                    },
                    fontSize = Title2,
                    color = TextColor,
                )
            }
        }
    }
}