package com.assistant.absolut.test.kinopoisk.presentation.bottom_navigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.assistant.absolut.test.kinopoisk.presentation.destinations.Destination
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.BottomNavSelected
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.BottomNavUnselected
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.DarkBackground
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.PaddingTopBottomNavItem
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.SizeImgBottomNav
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.TextBottomNav



@Composable
fun BottomBar(
    currentDestination: Destination,
    onDestinationSelected: (BottomBarDestinationUiModel) -> Unit,
    modifier: Modifier = Modifier
) {

    HorizontalDivider()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = PaddingTopBottomNavItem)
            .navigationBarsPadding()
            .background(DarkBackground),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarDestinationUiModel.entries.forEach { destination ->
            BottomBarItem(
                destination = destination,
                isSelected = currentDestination == destination.direction,
                onClick = { onDestinationSelected(destination) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    destination: BottomBarDestinationUiModel,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.1f else 1f,
        animationSpec = tween(durationMillis = 200),
        label = ""
    )

    Box(
        modifier = modifier
            .scale(scale)
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(
                    id = if (isSelected) destination.filledIcon else destination.outlinedIcon
                ),
                contentDescription = null,
                modifier = Modifier.size(SizeImgBottomNav),
                colorFilter = ColorFilter.tint(
                    if (isSelected) BottomNavSelected else BottomNavUnselected
                )
            )
            Text(
                text = stringResource(destination.label),
                color = if (isSelected) BottomNavSelected else BottomNavUnselected,
                fontSize = TextBottomNav
            )
        }
    }
}
