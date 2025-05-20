package com.assistant.absolut.test.kinopoisk.presentation.bottom_navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.components.NavGraphs
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.components.appCurrentDestinationAsState
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.components.destinations.Destination
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.components.startAppDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun BottomBar(
    navigator: DestinationsNavigator,
    navController: NavController
) {
    val currentDestination: Destination? = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    NavigationBar {
        BottomBarDestination.entries.forEach { destination ->
            NavigationBarItem(
                selected = currentDestination == destination.direction,
                onClick = {
                    navigator.navigate(destination.direction) {
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = ""
                    )
                },
                label = { Text(stringResource(destination.label)) }
            )
        }
    }
}