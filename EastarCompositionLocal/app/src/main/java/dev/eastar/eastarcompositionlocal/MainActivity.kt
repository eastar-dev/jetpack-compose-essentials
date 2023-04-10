package dev.eastar.eastarcompositionlocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.eastar.eastarcompositionlocal.ui.theme.EastarCompositionLocalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EastarCompositionLocalTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Composable1()
                }
            }
        }
    }
}


val LocalColor = staticCompositionLocalOf { Color.Blue }

@Composable
fun Composable1() {
    var color = if (isSystemInDarkTheme()) {
        Color.Red
    } else {
        Color.Green
    }


    Column {
        Composable2()
        CompositionLocalProvider(LocalColor.provides(color)) {
            Composable3()
        }
    }
}
///////////////////////////////////////////////////////////////////

@Composable
fun Composable2() {
    Text("Composable 2", modifier = Modifier.background(color = LocalColor.current))
    Composable4()
}


@Composable
fun Composable4() {
    Text("Composable 4", modifier = Modifier.background(color = LocalColor.current))
    Composable6()
}


@Composable
fun Composable6() {
    Text("Composable 6", modifier = Modifier.background(color = LocalColor.current))
}

///////////////////////////////////////////////////////////////////
@Composable
fun Composable3() {
    Text("Composable 3", modifier = Modifier.background(color = LocalColor.current))
    Composable5()
}

@Composable
fun Composable5() {
    Text("Composable 5", modifier = Modifier.background(color = LocalColor.current))
    Composable7()
    Composable8()
}

@Composable
fun Composable7() {
    Text("Composable 7", modifier = Modifier.background(color = LocalColor.current))
}

@Composable
fun Composable8() {
    Text("Composable 8", modifier = Modifier.background(color = LocalColor.current))
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EastarCompositionLocalTheme {
        Composable1()
    }
}