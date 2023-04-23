package dev.eastar.gesture

import android.log.Log
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.eastar.gesture.ui.theme.EastarGestureTheme

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
fun MainScreen(modifier: Modifier = Modifier) {
    ClickZoneChake()
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
fun GreetingPreview() {
    EastarGestureTheme {
        MainScreen()
    }
}