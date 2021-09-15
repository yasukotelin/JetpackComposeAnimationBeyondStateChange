package com.github.yasukotelin.jetpackcomposeanimationsbeyondthestatechange.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun Triangle(modifier: Modifier = Modifier) {
    val triangleShape = GenericShape { size, _ ->
        moveTo(size.width / 2f, 0f)
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
    }
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(triangleShape)
            .background(Color(0xFFC5C4C6))
    )
}

@Composable
fun animateValues(
    values: List<Float>,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Float> {
    // 1. Create the groups zipping with next entry
    val groups by rememberUpdatedState(newValue = values.zipWithNext())
    // 2. Start the state with the first value
    val state = remember { mutableStateOf(values.first()) }
    LaunchedEffect(key1 = groups) {
        val (_, setValue) = state
        // Start the animation from 0 to groups quantity
        animate(
            initialValue = 0f,
            targetValue = groups.size.toFloat(),
            animationSpec = animationSpec,
        ) { frame, _ ->
            // Get which group is being evaluated
            val integerPart = frame.toInt()
            val (initialValue, finalValue) = groups[frame.toInt()]
            // Get the current "position" from the group animation
            val decimalPart = frame - integerPart
            // Calculate the progress between the initial and final value
            setValue(
                initialValue + (finalValue - initialValue) * decimalPart
            )
        }
    }
    return state
}

@Composable
fun TriangleSkewSpinIndicator() {
    val animationSpec = infiniteRepeatable<Float>(
        animation = tween(
            durationMillis = 1800,
            easing = LinearEasing,
        )
    )
    val xRotation by animateValues(
        values = listOf(0f, 180f, 180f, 0f, 0f),
        animationSpec = animationSpec
    )
    val yRotation by animateValues(
        values = listOf(0f, 0f, 180f, 180f, 0f),
        animationSpec = animationSpec
    )
    Triangle(
        modifier = Modifier.graphicsLayer(
            rotationX = xRotation,
            rotationY = yRotation,
        )
    )
}