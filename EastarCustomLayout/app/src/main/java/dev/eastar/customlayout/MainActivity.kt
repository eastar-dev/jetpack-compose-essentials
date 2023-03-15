package dev.eastar.customlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.eastar.customlayout.ui.theme.EastarCustomLayoutTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EastarCustomLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Box(modifier = Modifier.size(120.dp, 80.dp)) {
        ColorBox(
            Modifier
                .exampleLayout(-1.0f)
                .background(Color.Blue)
        )
    }

}

@Composable
fun ColorBox(modifier: Modifier = Modifier.size(120.dp, 80.dp)) {
    Box(
        modifier = Modifier
            .padding(1.dp)
            .size(50.dp, 10.dp)
            .then(modifier)
    )
}

fun Modifier.exampleLayout(
    fraction: Float
) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)

    val x = -(placeable.width * fraction).roundToInt()

    layout(placeable.width, placeable.height) {
        placeable.placeRelative(x = x, y = 0)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EastarCustomLayoutTheme {
        MainScreen()
    }
}