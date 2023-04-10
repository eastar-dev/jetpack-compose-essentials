package dev.eastar.viewmodel

import android.log.Log
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.eastar.viewmodel.ui.theme.EastarViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EastarViewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ScreenSetup(viewModel())
                }
            }
        }
    }
}


@Composable
fun ScreenSetup(viewModel: MainViewModel = MainViewModel()) {
    MainScreen(
        isFahrenheit = viewModel.isFahrenheit,
        convertedTemperature = viewModel.convertedTemperature,
        onSwitchTemperatureType = { viewModel.switchTemperatureType() },
        onConvertTemperature = { viewModel.convertTemperature(it) }
    )
    //val isFahrenheit = viewModel.isFahrenheit
    //val convertedTemperature = viewModel.convertedTemperature
    //val onSwitchTemperatureType = { viewModel.switchTemperatureType() }
    //val onConvertTemperature: (String) -> Unit = { viewModel.convertTemperature(it) }
    //MainScreen(
    //    isFahrenheit = isFahrenheit,
    //    convertedTemperature = convertedTemperature,
    //    onSwitchTemperatureType = onSwitchTemperatureType,
    //    onConvertTemperature = onConvertTemperature
    //)
}

@Composable
fun MainScreen(isFahrenheit: Boolean, convertedTemperature: String, onSwitchTemperatureType: () -> Unit, onConvertTemperature: (String) -> Unit) {
    Log.e("MainScreen", isFahrenheit, convertedTemperature, onSwitchTemperatureType, onConvertTemperature)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        var textState by remember { mutableStateOf("") }
        val onTextChange = { text: String ->
            textState = text
        }

        Text(
            "Temperature Converter",
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.titleLarge
        )

        InputRow(isFahrenheit, onSwitchTemperatureType, textState , onTextChange )

        Text(text = convertedTemperature, style = MaterialTheme.typography.bodyLarge)

        Button(onClick = { onConvertTemperature(textState) }) {
            Text(text = "Convert Temperature")
        }
    }


    //InputRow(isFahrenheit = isFahrenheit, temperature = temperature, onSwitchTemp = onSwitchTemp, onConvertTemp = onConvertTemp)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputRow(isFahrenheit: Boolean, onSwitchTemperatureType: () -> Unit, textState: String, onTextChange: (String) -> Unit) {
    Log.e("InputRow", isFahrenheit, onSwitchTemperatureType, textState, onTextChange)
    Row(verticalAlignment = Alignment.CenterVertically) {
        Switch(checked = isFahrenheit,
            onCheckedChange = { onSwitchTemperatureType() }
        )

        OutlinedTextField(
            value = textState,
            onValueChange = onTextChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            label = { Text(text = "Enter Temperature") },
            modifier = Modifier.padding(10.dp),
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ac_unit_24),
                    contentDescription = "frost",
                    modifier = Modifier.size(40.dp),
                )
            }
        )

        Crossfade(targetState = isFahrenheit, animationSpec = tween(200), label = "") {
            if (it) {
                Text(text = "\u2109", style = MaterialTheme.typography.bodyLarge)
            } else {
                Text(text = "\u2103", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview(viewModel: MainViewModel = MainViewModel()) {
    EastarViewModelTheme {
        MainScreen(
            isFahrenheit = viewModel.isFahrenheit,
            convertedTemperature = viewModel.convertedTemperature,
            onSwitchTemperatureType = viewModel::switchTemperatureType,
            onConvertTemperature = viewModel::convertTemperature
        )
    }
}