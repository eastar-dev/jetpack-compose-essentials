package dev.eastar.customlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.eastar.customlayout.ui.theme.EastarCustomLayoutTheme

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
    Box {
        CascadeLayout(spacing = 20) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Blue)
            )
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Magenta)
            )
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Cyan)
            )
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Gray)
            )
        }
    }
}


@Composable
fun CascadeLayout(
    spacing: Int = 0,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        var indent = 0
        layout(constraints.maxWidth, constraints.maxHeight) {
            val placeables = measurables.map { measurable ->
                measurable.measure(constraints)
            }
            var yCoord = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(x = indent, y = yCoord)
                indent += placeable.width + spacing
                yCoord += placeable.height + spacing
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EastarCustomLayoutTheme {
        MainScreen()
    }
}