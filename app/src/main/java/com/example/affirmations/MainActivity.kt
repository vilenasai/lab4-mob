package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.DataSource
import com.example.affirmations.model.Topic
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicApp()
                }
            }
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 1. Картинка темы 68dp, скруглены только левые углы
            Image(
                painter = painterResource(topic.imageRes),
                contentDescription = stringResource(topic.name),
                modifier = Modifier
                    .size(68.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 12.dp,
                            bottomStart = 12.dp
                        )
                    ),
                contentScale = ContentScale.Crop
            )

            // 2. Текстовая часть
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            ) {
                // Название (bodyMedium)
                Text(
                    text = stringResource(topic.name),
                    style = MaterialTheme.typography.bodyMedium
                )

                // Отступ 8dp
                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // 3. ТВОЯ КАРТИНКА-ИКОНКА (18dp)
                    Image(
                        painter = painterResource(id = R.drawable.dots_icon), // имя твоего файла
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )

                    // Отступ 8dp
                    Spacer(modifier = Modifier.width(8.dp))

                    // 4. Число (labelMedium)
                    Text(
                        text = topic.availableCourses.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Composable
fun TopicGrid(topics: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(topics) { topic ->
            TopicCard(topic = topic)
        }
    }
}

@Composable
fun TopicApp() {
    TopicGrid(
        topics = DataSource.topics,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    )
}

@Preview(showBackground = true)
@Composable
fun TopicAppPreview() {
    AffirmationsTheme {
        TopicApp()
    }
}