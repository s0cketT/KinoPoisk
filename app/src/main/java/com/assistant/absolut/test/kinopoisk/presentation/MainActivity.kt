package com.assistant.absolut.test.kinopoisk.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.assistant.absolut.test.kinopoisk.presentation.bottom_navigation.BottomBar
import com.assistant.absolut.test.kinopoisk.presentation.home_screen.components.NavGraphs
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.KinopoiskTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.utils.toDestinationsNavigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KinopoiskTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomBar(
                            navController = navController,
                            onDestinationSelected = { destination ->
                                navController.toDestinationsNavigator().navigate(
                                    destination.direction,
                                    builder = { launchSingleTop = true }
                                )
                            },

                        )
                    }
                ) { innerPadding ->
                    DestinationsNavHost(
                        navController = navController,
                        navGraph = NavGraphs.root,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


