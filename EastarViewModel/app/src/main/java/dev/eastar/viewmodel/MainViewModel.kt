package dev.eastar.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class MainViewModel : ViewModel() {

    var isFahrenheit by mutableStateOf(true)
    var result by mutableStateOf("")


    fun convertTemp(temp: String) {
        result = kotlin.runCatching {
            val tempInt = temp.toInt()

            if (isFahrenheit) {
                "${fahrenheitToCelsius(tempInt)}°C"
            } else {
                "${celsiusToFahrenheit(tempInt)}°F"
            }
        }.getOrDefault("Invalid Entry")
    }

    fun switchTemp() {
        isFahrenheit = !isFahrenheit
    }

    private fun celsiusToFahrenheit(tempInt: Int): String {
        return ((tempInt * 1.8) + 32).roundToInt().toString()
    }

    private fun fahrenheitToCelsius(tempInt: Int): String {
        return ((tempInt - 32) * 0.5556).roundToInt().toString()
    }
}