package com.assistant.absolut.test.kinopoisk.presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.assistant.absolut.test.kinopoisk.R
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.HeartGradientColors
import com.assistant.absolut.test.kinopoisk.presentation.ui.theme.SizeLoadIconDp

@Composable
fun CustomLoader(
    modifier: Modifier = Modifier,
    iconRes: Int = R.drawable.load,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1400, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ), label = ""
    )

    val animatedScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val animatedColor by infiniteTransition.animateColor(
        initialValue = HeartGradientColors.first(),
        targetValue = HeartGradientColors.last(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(500)
        ), label = ""
    )

    Box(
        modifier = modifier.fillMaxSize().padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = "",
            modifier = Modifier
                .size(SizeLoadIconDp)
                .rotate(rotation)
                .scale(animatedScale),
            colorFilter = ColorFilter.tint(animatedColor)
        )
    }
}