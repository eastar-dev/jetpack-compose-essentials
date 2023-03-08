package dev.eastar.eastarslopapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.eastar.eastarslopapi.ui.theme.EastarSlopApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EastarSlopApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }

}

@Composable
fun MainScreen() {
    var linearSclected by remember { mutableStateOf(true) }
    var imageSelected by remember { mutableStateOf(true) }
    val onLinearClick = { value: Boolean ->
        imageSelected = value
    }
    val onTitleClick = { value: Boolean ->
        linearSclected = value
    }

    ScreenContent(
        linearSelected = linearSclected,
        imageSelected = imageSelected,
        onTitleClick = onTitleClick,
        onLinearClick = onLinearClick,
        titleContent = {
            if (imageSelected) {
                TitleImage(drawing = R.drawable.ic_baseline_cloud_download_24)
            } else {
                Text(
                    text = "Downloading",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(30.dp)
                )
            }
        },
        progressContent = {
            if (linearSclected) {
                LinearProgressIndicator(Modifier.height(40.dp))
            } else {
                CircularProgressIndicator(Modifier.size(200.dp), strokeWidth = 20.dp)
            }
        }
    )
}

@Composable
fun ScreenContent(
    linearSelected: Boolean,
    imageSelected: Boolean,
    onTitleClick: (Boolean) -> Unit,
    onLinearClick: (Boolean) -> Unit,
    titleContent: @Composable () -> Unit,
    progressContent: @Composable () -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        titleContent()
        progressContent()
        CheckBoses(linearSclected = linearSelected, imageSelected = imageSelected, onTitleClick = onTitleClick, onLinearClick = onLinearClick)
    }
}


@Composable
fun CheckBoses(
    linearSclected: Boolean,
    imageSelected: Boolean,
    onTitleClick: (Boolean) -> Unit,
    onLinearClick: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = imageSelected,
            onCheckedChange = onLinearClick,

            )
        Text(text = "Image Title")
        Spacer(modifier = Modifier.weight(20f))
        Checkbox(
            checked = linearSclected,
            onCheckedChange = onTitleClick,
        )
        Text(text = "Linear Title")
    }
}

@Composable
fun TitleImage(drawing: Int) {
    Image(
        painter = painterResource(id = drawing),
        contentDescription = "Title Image",
    )
}


@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}

