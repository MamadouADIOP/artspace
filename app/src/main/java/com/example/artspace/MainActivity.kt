package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpace(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    var resourceId by remember { mutableIntStateOf(R.drawable.image_1) }
    var title by remember { mutableStateOf(R.string.title_1) }
    var artist by remember { mutableStateOf(R.string.artist_1) }
    var imageDescriptionId by remember { mutableStateOf(R.string.description_1) }
    var year by remember { mutableIntStateOf(R.string.year_1) }
    var currentArtWork by remember { mutableIntStateOf(1) }

    var nextBehavior = {

        when (currentArtWork) {
            1 -> {
                resourceId = R.drawable.image_2
                title = R.string.title_2
                year = R.string.year_2
                artist = R.string.artist_2
                imageDescriptionId = R.string.description_1
                currentArtWork = 2
            }

            2 -> {
                resourceId = R.drawable.image_3
                title = R.string.title_3
                year = R.string.year_3
                artist = R.string.artist_3
                imageDescriptionId = R.string.description_2
                currentArtWork = 3
            }

            3 -> {
                resourceId = R.drawable.image_1
                title = R.string.title_1
                year = R.string.year_1
                artist = R.string.artist_1
                imageDescriptionId = R.string.description_3
                currentArtWork = 1
            }

        }
    }

    var previousBehavior = {

        when (currentArtWork) {
            1 -> {
                resourceId = R.drawable.image_3
                title = R.string.title_3
                year = R.string.year_3
                artist = R.string.artist_3
                imageDescriptionId = R.string.description_3
                currentArtWork = 3
            }

            2 -> {
                resourceId = R.drawable.image_1
                title = R.string.title_1
                year = R.string.year_1
                artist = R.string.artist_1
                imageDescriptionId = R.string.description_1
                currentArtWork = 1
            }

            3 -> {
                resourceId = R.drawable.image_2
                title = R.string.title_2
                year = R.string.year_2
                artist = R.string.artist_2
                imageDescriptionId = R.string.description_2
                currentArtWork = 2
            }

        }
    }
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top=16.dp, start = 32.dp, end =32.dp)
                .weight(7f).wrapContentSize(),
            content = ArtWorkSpace(resourceId, imageDescriptionId)
        )
        Row(
            Modifier
                .weight(0.5f)
                .padding(start =32.dp, end = 32.dp)
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .fillMaxSize(),
                content = ArtistPresentation(title, artist, year)
            )
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .weight(1f)
                .padding(32.dp)
                .fillMaxWidth(),
            content = NavigationSection(nextBehavior, previousBehavior)
        )
    }
}

@Composable
private fun ArtWorkSpace(
    @DrawableRes resourceId: Int, @StringRes imageDescriptionId: Int
): @Composable() (RowScope.() -> Unit) = {
    Surface(
        tonalElevation = 32.dp,
        modifier = Modifier.padding(bottom = 10.dp),
        color = MaterialTheme.colorScheme.onPrimary
    ) {
        Image(
            painter = painterResource(id = resourceId),
            contentDescription = stringResource(imageDescriptionId),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(32.dp).wrapContentSize()
                .border(
                    BorderStroke(1.dp, Color.Gray), RectangleShape
                )
        )
    }
}

@Composable
private fun ArtistPresentation(
    @StringRes title: Int, @StringRes artist: Int, @StringRes year: Int
): @Composable() (ColumnScope.() -> Unit) = {
    Text(
        text = "${stringResource(id = title)}", modifier = Modifier
            .align(Alignment.Start)
            .padding(start = 16.dp)
    )
    Spacer(modifier = Modifier.size(2.dp))
    Row(
        Modifier
            .align(Alignment.Start)
            .padding(start = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "${stringResource(id = artist)} ",
            modifier = Modifier,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "(${stringResource(id = year)})",
            modifier = Modifier
        )
    }
}

@Composable
private fun NavigationSection(
    nextBehavior: () -> Unit,
    previousBehavior: () -> Unit
): @Composable() (RowScope.() -> Unit) = {
    Button(
        onClick = previousBehavior, modifier = Modifier
            .height(60.dp)
            .width(150.dp)
    ) {
        Text(
            text = stringResource(R.string.previous)
        )

    }
    Spacer(modifier = Modifier.weight(1f))
    Button(
        onClick = nextBehavior, modifier = Modifier
            .height(60.dp)
            .width(150.dp)
    ) {
        Text(
            text = stringResource(R.string.next)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}