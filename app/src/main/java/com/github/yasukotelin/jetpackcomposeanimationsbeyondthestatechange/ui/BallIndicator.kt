package com.github.yasukotelin.jetpackcomposeanimationsbeyondthestatechange.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun Ball(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color(0xFFC5C4C6))
    )
}

@Composable
fun BallScaleIndicator() {
    val infiniteTransition = rememberInfiniteTransition()

    val animationProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(500))
    )

    Ball(
        modifier = Modifier
            .scale(animationProgress)
            .alpha(1 - animationProgress)
    )
}

@Composable
fun BallPulseSyncIndicator() {
    val animationValues = (1..3).map { index ->
        var animatedValue by remember { mutableStateOf(0f) }

        LaunchedEffect(key1 = Unit) {
            delay(70L * index)

            animate(
                initialValue = 0f,
                targetValue = 12f,
                animationSpec = infiniteRepeatable(
                    animation = tween(300),
                    repeatMode = RepeatMode.Reverse
                )
            ) { value, _ -> animatedValue = value }
        }

        animatedValue
    }

    Row {
        animationValues.forEach { animatedValue ->
            Ball(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .offset(y = animatedValue.dp)
            )
        }
    }
}