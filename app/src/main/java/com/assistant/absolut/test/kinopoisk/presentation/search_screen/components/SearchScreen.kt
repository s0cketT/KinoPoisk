package com.assistant.absolut.test.kinopoisk.presentation.home_screen.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.assistant.absolut.test.kinopoisk.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@Destination
@RootNavGraph
@Composable
fun SearchScreen() {
    Text(
        text = stringResource(id = R.string.search_screen)
    )
}