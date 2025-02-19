package dev.eastar.eastarrowcolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.eastar.eastarrowcolumn.ui.theme.EastarRowColumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EastarRowColumnTheme {
                TextRow2()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .size(width = 250.dp, height = 500.dp)
            .background(Color(0x55ff0000))
    ) {
        TextCell("9", Modifier.align(Alignment.Start))
        TextCell("10", Modifier.weight(0.5f, false), Color.Blue)
        TextCell(
            "11",
            Modifier
                .weight(0.5f, true)
                .align(Alignment.End)
        )
    }
}

@Composable
fun TextRow() {
    Row {
        Text(
            modifier = Modifier.alignByBaseline(),
            text = "Large Test",
            fontSize = 70.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = "Small text",
            fontSize = 30.sp,
            modifier = Modifier.alignByBaseline(),
            fontWeight = FontWeight.Bold,
        )
    }
}
@Composable
fun TextRow2() {
    Row {
        Text(
            text = "1stLine 한글이면 어떻까?\n2stLint 이것이 한글이면",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.alignBy(FirstBaseline)
        )

        Text(
            text = "Small text",
            fontSize = 30.sp,
            modifier = Modifier.paddingFrom(alignmentLine = FirstBaseline ,before = 140.dp , after = 0.dp),
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun TextCell(text: String, modifier: Modifier = Modifier, borderColor: Color = Color.Red) {

    val cellModifier = Modifier
        .padding(4.dp)
        .size(100.dp, 100.dp)
        .border(width = 4.dp, color = borderColor)

    Text(
        text = text, cellModifier.then(modifier),
        fontSize = 70.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun DefaultPreview4() {
    EastarRowColumnTheme {
        TextRow2()
    }
}
@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun DefaultPreview3() {
    EastarRowColumnTheme {
        TextRow()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    EastarRowColumnTheme {
        MainScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    EastarRowColumnTheme {
        MainScreen()
    }
}