package dev.eastar.eastarcoroutinecomposable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.eastar.eastarcoroutinecomposable.ui.theme.EastarCoroutineComposableTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EastarCoroutineComposableTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )


    val coroutineScope = rememberCoroutineScope()
    //error
    //coroutinescoe.launch {
    //     //do something
    //}

    LaunchedEffect(key1 = Unit){
        coroutineScope.launch {
            delay(1000)
        }
    }


    SideEffect {
        coroutineScope.launch {
            delay(1000)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EastarCoroutineComposableTheme {
        Greeting("Android")
    }
}