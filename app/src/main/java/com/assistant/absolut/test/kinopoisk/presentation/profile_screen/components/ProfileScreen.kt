package com.assistant.absolut.test.kinopoisk.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.assistant.absolut.test.kinopoisk.R
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.DarkBackground
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph
@Destination
@Composable
fun ProfileScreen() {

    Box(modifier = Modifier.fillMaxSize().background(DarkBackground)) {
        Text(stringResource(id = R.string.profile_screen))
    }

}