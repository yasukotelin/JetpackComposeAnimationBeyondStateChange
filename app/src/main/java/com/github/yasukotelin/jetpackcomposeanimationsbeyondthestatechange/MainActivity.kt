package com.github.yasukotelin.jetpackcomposeanimationsbeyondthestatechange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.yasukotelin.jetpackcomposeanimationsbeyondthestatechange.ui.BallPulseSyncIndicator
import com.github.yasukotelin.jetpackcomposeanimationsbeyondthestatechange.ui.BallScaleIndicator
import com.github.yasukotelin.jetpackcomposeanimationsbeyondthestatechange.ui.TriangleSkewSpinIndicator
import com.github.yasukotelin.jetpackcomposeanimationsbeyondthestatechange.ui.theme.JetpackComposeAnimationsBeyondTheStateChangeTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAnimationsBeyondTheStateChangeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Content() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TitleWithContent(title = "Ball Scale Indicator") {
            BallScaleIndicator()
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
        )
        TitleWithContent(title = "Ball Pulse Sync Indicator") {
            BallPulseSyncIndicator()
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
        )
        TitleWithContent(title = "Triangle Skew Spin Indicator") {
            TriangleSkewSpinIndicator()
        }
    }
}

@Composable
fun Title(title: String) {
    Text(text = title, fontSize = 20.sp)
}

@Composable
fun TitleWithContent(title: String, content: @Composable () -> Unit) {
    Title(title)
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(28.dp)
    )
    content()
}

@Preview
@Composable
fun DefaultPreview() {
    JetpackComposeAnimationsBeyondTheStateChangeTheme() {
        Surface(color = MaterialTheme.colors.background) {
            Content()
        }
    }
}
