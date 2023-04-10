package dev.eastar.viewmodel

import android.log.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class MainViewModel : ViewModel() {

    var isFahrenheit by mutableStateOf(true)
    var convertedTemperature by mutableStateOf("")


    fun convertTemperature(temp: String) {
        convertedTemperature = kotlin.runCatching {
            val tempInt = temp.toInt()

            if (isFahrenheit) {
                "${fahrenheitToCelsius(tempInt)}°C"
            } else {
                "${celsiusToFahrenheit(tempInt)}°F"
            }
        }.getOrDefault("Invalid Entry")
    }

    fun switchTemperatureType() {
        Log.e("switchTemperatureType",isFahrenheit)
        isFahrenheit = !isFahrenheit
    }

    private fun celsiusToFahrenheit(tempInt: Int): String {
        return ((tempInt * 1.8) + 32).roundToInt().toString()
    }

    private fun fahrenheitToCelsius(tempInt: Int): String {
        return ((tempInt - 32) * 0.5556).roundToInt().toString()
    }
}