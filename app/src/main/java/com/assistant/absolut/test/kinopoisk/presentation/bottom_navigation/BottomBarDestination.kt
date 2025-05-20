package com.assistant.absolut.test.kinopoisk.presentation.bottom_navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.assistant.absolut.test.kinopoisk.R
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.components.destinations.HomeScreenDestination
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.components.destinations.ProfileScreenDestination
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.components.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec


enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    Home(HomeScreenDestination, Icons.Default.Home, R.string.home_screen),
    Search(SearchScreenDestination, Icons.Default.Search, R.string.search_screen),
    Profile(ProfileScreenDestination, Icons.Filled.Person, R.string.profile_screen)
}