package com.assistant.absolut.test.kinopoisk.presentation.bottom_navigation

import androidx.annotation.StringRes
import com.assistant.absolut.test.kinopoisk.R
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.components.destinations.HomeScreenDestination
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.components.destinations.ProfileScreenDestination
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.components.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.spec.Direction

enum class BottomBarDestinationUiModel(
    val direction: Direction,
    val filledIcon: Int,
    val outlinedIcon: Int,
    @StringRes val label: Int
) {
    HOME(HomeScreenDestination, R.drawable.home_sel, R.drawable.home_un_sel, R.string.home_screen),
    SEARCH(SearchScreenDestination, R.drawable.search, R.drawable.search, R.string.search_screen),
    PROFILE(ProfileScreenDestination, R.drawable.profile_sel, R.drawable.profile_un_sel, R.string.profile_screen)
}