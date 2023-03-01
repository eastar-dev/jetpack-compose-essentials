@file:OptIn(ExperimentalMaterial3Api::class)

package dev.eastar.composestate

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.eastar.composestate.ui.theme.ComposeStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStateTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    DemoScreen()
                }
            }
        }
    }
}

@Composable
fun DemoScreen() {
    MyTextField()
}

@Composable
fun MyTextField() {
    //00
    //val textState = remember { mutableStateOf("") }
    //val onTextChange = { test: String ->
    //    textState.value = test
    //}
    //TextField(value = textState.value, onValueChange = onTextChange)

    //01
    //var textState by remember { mutableStateOf("") }
    //val onTextChange = { test: String ->
    //    textState = test
    //}
    //TextField(value = textState, onValueChange = onTextChange)

    //02
    //var (textValue, setText) = remember { mutableStateOf("") }
    //val onTextChange = { test: String ->
    //    setText(test)
    //}
    //TextField(value = textValue, onValueChange = onTextChange)

    //03
    val (text, onTextChange) = remember { mutableStateOf("") }
    TextField(value = text, onValueChange = onTextChange)
}

//@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeStateTheme {
        DemoScreen()
    }
}