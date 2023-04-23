package dev.eastar.gesture

import android.log.Log
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import dev.eastar.gesture.ui.theme.EastarGestureTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EastarGestureTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(initScreenState: Int = 3, modifier: Modifier = Modifier) {
    var screenState by remember { mutableStateOf(initScreenState) }
    Row {
        Column {
            ClickZoneChake()
            Button(onClick = { screenState = (screenState + 1) % 3 }) {
                Text(text = "$screenState")
            }
        }

        when (screenState) {
            0 -> Column {
                ClickDemo()
                TabPressDemo()
                DrawDemo()
                PointerInputDrag()
            }

            1 -> Column {
                ScrollableModifierDemo()
            }

            2 -> Column {
                ScrollModifierDemo()
            }

            3 -> Column {
                MultiTou()
            }
        }
    }
}

@Composable
fun ScrollModifierDemo() {
    val image = ImageBitmap.imageResource(R.drawable.vacation)

    val v = with(LocalDensity.current) {
        ((270 - 150) / 2).dp.roundToPx()
    }
    val h = with(LocalDensity.current) {
        ((360 - 150) / 2).dp.roundToPx()
    }

    Box(
        modifier = Modifier
            .debug()
            .size(150.dp)
            .verticalScroll(rememberScrollState(v))
            .horizontalScroll(rememberScrollState(h))
    ) {
        Canvas(
            modifier = Modifier
                .size(360.dp, 270.dp)
        ) {
            drawImage(
                image, topLeft = Offset(10.dp.toPx(), 0F)
            )
        }
    }
}

@Composable
fun ScrollableModifierDemo() {
    var offset by remember { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .debug()
            .fillMaxSize()
            .size(100.dp)
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollableState { delta ->
                    offset += delta
                    delta
                }
            )
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .offset { IntOffset(0, offset.roundToInt()) }
                .background(Color.Red)
        )
    }
}

@Composable
fun PointerInputDrag() {
    Box(
        modifier = Modifier
            .debug()
            .fillMaxSize()
    ) {
        var xOffset by remember { mutableStateOf(0f) }
        var yOffset by remember { mutableStateOf(0f) }
        Box(
            modifier = Modifier
                .offset { IntOffset(xOffset.roundToInt(), yOffset.roundToInt()) }
                .size(100.dp)
                .background(Color.Magenta)
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        xOffset += dragAmount.x
                        yOffset += dragAmount.y
                    }
                }
        )
    }
}

@Composable
fun DrawDemo() {
    Box(
        modifier = Modifier
            .debug()
            .fillMaxWidth()
    ) {
        var xOffset by remember { mutableStateOf(0f) }
        Box(
            modifier = Modifier
                .offset { IntOffset(xOffset.roundToInt(), 0) }
                .size(100.dp)
                .background(Color.Blue)
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        xOffset += delta
                    }
                )
        )
    }
}

@Composable
fun TabPressDemo() {
    var textState by remember { mutableStateOf("Waiting ...") }
    val tabHandler = { state: String -> textState = state }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .debug()
            .fillMaxWidth()
    ) {
        Box(
            Modifier
                .padding(10.dp)
                .background(Color.Blue)
                .size(100.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = { tabHandler("Double Tab") },
                        onLongPress = { tabHandler("Long Tab") },
                        onTap = { tabHandler("Tab") },
                        onPress = { tabHandler("Press") },
                    )
                }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(textState)

    }
}

private fun Modifier.debug() = this.then(Modifier.border(1.dp, Color.Red.copy(alpha = 0.5F)))

@Composable
fun ClickDemo() {

    var colorState by remember { mutableStateOf(true) }
    var bgColor by remember { mutableStateOf(Color.Blue) }

    val clickHandler = {
        colorState = !colorState
        bgColor = if (colorState) Color.Blue else Color.DarkGray
    }

    Box {
        Box(Modifier
            .clickable { clickHandler() }
            .background(bgColor)
            .size(100.dp)
        ) {

        }
    }
}

@Composable
fun ClickZoneChake() {
    Row {
        Column {
            repeat(10) {
                SomeComposableV(it + 48 - 10 / 2)
            }
        }
        Column {
            repeat(10) {
                SomeComposableH(it + 48 - 10 / 2)
            }
        }
    }
}

@Composable
fun SomeComposableV(width: Int = 32) {
    Text(text = "$width dp", modifier = Modifier
        .size(width.dp, 48.dp)
        .background(color = Color.hsv(((20 * (width - 32)) % 360).toFloat(), 1F, 1F))
        .clickable { Log.e("SomeComposable") })
}

@Composable
fun SomeComposableH(height: Int = 32) {
    Text(text = "$height dp", modifier = Modifier
        .size(48.dp, height.dp)
        .background(color = Color.hsv(((20 * (height - 32)) % 360).toFloat(), 0.5F, 1F))
        .clickable { Log.e("SomeComposable") })
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    EastarGestureTheme {
        MainScreen()
    }
}