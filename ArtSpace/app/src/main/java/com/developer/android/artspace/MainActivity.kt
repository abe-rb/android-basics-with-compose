package com.developer.android.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developer.android.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

data class Artwork(
    @StringRes val titleRes: Int,
    @StringRes val artistRes: Int,
    @StringRes val yearRes: Int,
    @DrawableRes val imageRes: Int
)

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    val artworks = listOf(
        Artwork(R.string.art_1_title, R.string.art_1_artist,R.string.art_1_year, R.drawable.art_1_drawable),
        Artwork(R.string.art_2_title, R.string.art_2_artist,R.string.art_2_year, R.drawable.art_2_drawable),
        Artwork(R.string.art_3_title, R.string.art_3_artist,R.string.art_3_year, R.drawable.art_3_drawable),
    )

    var index by remember { mutableIntStateOf(0) }
    val onClickPrev: () -> Unit = {
        if (index > 0) index-- else index = artworks.size - 1
    }
    val onClickNext: () -> Unit = {
        if (index < artworks.size - 1) index++ else index = 0
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { contentPadding ->
        Column(
            modifier = Modifier
            .padding(contentPadding)
            .padding(bottom = 32.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            ArtworkWall(painterResource(artworks[index].imageRes), modifier = Modifier.weight(3f))
            Spacer(modifier = Modifier.weight(1f))
            ArtworkDescriptor(
                title = stringResource(artworks[index].titleRes),
                artist = stringResource(artworks[index].artistRes),
                year = stringResource(artworks[index].yearRes)
            )
            DisplayController(
                onClickPrev = onClickPrev,
                onClickNext = onClickNext
            )
        }
    }
}

@Composable
fun ArtworkWall(image: Painter, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier.padding(24.dp),
        shape = RectangleShape,
        border = BorderStroke(36.dp, Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
    ) {
        Box(modifier = Modifier.padding(36.dp)) {
            Image(
                painter = image,
                contentDescription = "artwork"
            )
        }
    }
}

@Composable
fun ArtworkDescriptor(
    title: String,
    artist: String,
    year: String?,
    modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(horizontal = 28.dp)
            .background(color = Color.LightGray)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = artist,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "($year)",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun DisplayController(
    onClickPrev: () -> Unit,
    onClickNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 32.dp)
    ) {
        Button(
            onClick = onClickPrev,
            modifier = Modifier.weight(1f)
        ) { Text(text = "Previous")}
        Button(
            onClick = onClickNext,
            modifier = Modifier.weight(1f)
        ) { Text(text = "Next")}
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceApp()
}