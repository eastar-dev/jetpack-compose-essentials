package dev.eastar.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.eastar.viewmodel.ui.theme.EastarViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EastarViewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ScreenSetup()
                }
            }
        }
    }
}


@Composable
fun ScreenSetup(viewModel: MainViewModel = MainViewModel()) {
    MainScreen(
        isFahrenheit = viewModel.isFahrenheit,
        result = viewModel.result,
        onSwitchTemp = viewModel::switchTemp,
        onConvertTemp = viewModel::convertTemp
    )
}

@Composable
fun MainScreen(isFahrenheit: Boolean, result: String, onSwitchTemp: () -> Unit, onConvertTemp: (String) -> Unit) {

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview(viewModel: MainViewModel = MainViewModel()) {
    EastarViewModelTheme {
        MainScreen(
            isFahrenheit = viewModel.isFahrenheit,
            result = viewModel.result,
            onSwitchTemp = viewModel::switchTemp,
            onConvertTemp = viewModel::convertTemp
        )
    }
}