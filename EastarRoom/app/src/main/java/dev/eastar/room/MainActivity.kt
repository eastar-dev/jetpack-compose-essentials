package dev.eastar.room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.eastar.room.ui.theme.EastarRoomTheme
import java.util.Calendar
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EastarRoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Route()
                }
            }
        }
    }
}

@Composable
fun Route() {
    Screen()
}


@Composable
fun Screen() {
    AnalogClock()
}

@Composable
fun AnalogClock() {
    val calendar = Calendar.getInstance()
    //val hours = calendar.get(Calendar.HOUR_OF_DAY) % 12
    //val minutes = calendar.get(Calendar.MINUTE)
    //val seconds = calendar.get(Calendar.SECOND)

    val hours = 3
    val minutes = 1
    val seconds = 2

    val hourDegree =  hours * 30f + minutes * 0.5f
    val minuteDegree = minutes * 6f
    val secondDegree = seconds * 6f

    Box(
        modifier = Modifier
            .size(240.dp)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                //.clip(CircleShape)
        ) {
            drawCircle(
                color = Color.White,
                radius = size.minDimension / 2,
                style = Stroke(width = 4.dp.toPx())
            )

            //drawArc(
            //    color = Color(0xFF212121),
            //    startAngle = 0f,
            //    sweepAngle = 360f,
            //    useCenter = true,
            //    style = Stroke(width = 4.dp.toPx())
            //)

            drawLine(
                color = Color.Blue,
                start = Offset(100.dp.toPx(),100.dp.toPx(),),
                end = Offset(
                    size.minDimension / 2 * cos(hourDegree.toRadians()),
                    size.minDimension / 2 * sin(hourDegree.toRadians())
                ),
                strokeWidth = 12.dp.toPx(),
                cap = StrokeCap.Round
            )

            drawLine(
                color = Color.Black,
                start = Offset(100.dp.toPx(),100.dp.toPx(),),
                end = Offset(
                    size.minDimension / 2.5f * cos(minuteDegree.toRadians()),
                    size.minDimension / 2.5f * sin(minuteDegree.toRadians())
                ),
                strokeWidth = 8.dp.toPx(),
                cap = StrokeCap.Round
            )

            drawLine(
                color = Color.Red,
                start = Offset(100.dp.toPx(),100.dp.toPx(),),
                end = Offset(
                    size.minDimension / 2.5f * cos(secondDegree.toRadians()),
                    size.minDimension / 2.5f * sin(secondDegree.toRadians())
                ),
                strokeWidth = 4.dp.toPx(),
                cap = StrokeCap.Round
            )
        }

        Text(text = "$hours:$minutes:$seconds")
    }
}

fun Float.toRadians(): Float {
    return Math.toRadians(this.toDouble()).toFloat()
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EastarRoomTheme {
        Screen()
    }
}