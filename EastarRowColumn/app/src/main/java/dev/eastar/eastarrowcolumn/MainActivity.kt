package dev.eastar.eastarrowcolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.eastar.eastarrowcolumn.ui.theme.EastarRowColumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EastarRowColumnTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Row {
        TextCell("1")
        TextCell("2")
        TextCell("3")
    }
}

@Composable
fun TextCell(text: String, modifier: Modifier = Modifier) {

    val cellModifier = Modifier
        .padding(4.dp)
        .size(100.dp, 100.dp)
        .border(width = 4.dp, color = Color.Red)

    Text(
        text = text, cellModifier.then(modifier),
        fontSize = 70.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    EastarRowColumnTheme {
        MainScreen()
    }
}