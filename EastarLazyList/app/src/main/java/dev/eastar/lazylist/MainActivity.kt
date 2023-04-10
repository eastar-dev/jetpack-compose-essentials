@file:OptIn(ExperimentalFoundationApi::class)

package dev.eastar.lazylist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import dev.eastar.lazylist.ui.theme.EastarLazyListTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val items by lazy { resources.getStringArray(R.array.car_array) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EastarLazyListTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen(items)
                }
            }
        }
    }
}

@Composable
fun MainScreen(items: Array<String>, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val groupedItems = items.groupBy { it.substringBefore(" ") }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val displayButton = listState.firstVisibleItemIndex > 5


    val onListItemClick = { text: String ->
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    Box {
        LazyColumn(
            modifier = modifier,
            state = listState,
            contentPadding = PaddingValues(40.dp),
        ) {
            groupedItems.forEach { (key, value) ->
                stickyHeader() {
                    Box(
                        Modifier
                            .background(Color.Magenta)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = key, color = Color.White, modifier = Modifier
                                .background(Color.DarkGray)
                                .fillMaxWidth(), style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                items(value) {
                    MyListItem(item = it, onItemClick = onListItemClick)
                }
            }
        }

        AnimatedVisibility(
            visible = displayButton,
            Modifier.align(Alignment.BottomCenter)
        ) {

            OutlinedButton(
                onClick = {
                    coroutineScope.launch {
                        listState.scrollToItem(0)
                    }
                },

                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.DarkGray
                ),
                modifier = Modifier
                    .background(Color.Magenta)
                    .padding(5.dp)
            ) {
                Text(text = "Top")
            }

        }
    }

    //MyListItem(item = items[0])
}

@Composable
fun MyListItem(item: String, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onItemClick(item) },
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ImageLoader(item)
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = item, modifier = Modifier.padding(8.dp), style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ImageLoader(item: String) {
    val url = "https://www.ebookfrenzy.com/book_examples/car_logos/" + item.substringBefore(" ") + "_logo.png"

    Image(
        painter = rememberImagePainter(url), contentDescription = "car image", contentScale = ContentScale.Inside, modifier = Modifier.size(75.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EastarLazyListTheme {
        MainScreen(
            arrayOf(
                "Buick Century",
                "Buick LaSabre"
            )
        )
    }
}