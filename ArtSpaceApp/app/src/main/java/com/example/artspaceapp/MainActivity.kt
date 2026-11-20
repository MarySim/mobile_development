package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

private const val ARTWORK_COUNT = 3

private data class Artwork(
    @param:DrawableRes val image: Int,
    @param:StringRes val title: Int,
    @param:StringRes val artist: Int,
    @param:StringRes val year: Int
)

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    var artworkIndex by rememberSaveable { mutableIntStateOf(0) }
    val artwork = when (artworkIndex) {
        0 -> Artwork(
            R.drawable.great_wave, R.string.great_wave_title, R.string.hokusai, R.string.great_wave_year
        )
        1 -> Artwork(
            R.drawable.self_portrait, R.string.self_portrait_title, R.string.van_gogh, R.string.self_portrait_year
        )
        else -> Artwork(
            R.drawable.wheat_field, R.string.wheat_field_title, R.string.van_gogh, R.string.wheat_field_year
        )
    }

    val previous = { artworkIndex = (artworkIndex + ARTWORK_COUNT - 1) % ARTWORK_COUNT }
    val next = { artworkIndex = (artworkIndex + 1) % ARTWORK_COUNT }
    BoxWithConstraints(
        modifier = modifier.fillMaxSize().safeDrawingPadding().padding(20.dp)
    ) {
        if (maxWidth > maxHeight && maxWidth >= 440.dp) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ArtworkFrame(artwork, modifier = Modifier.weight(3f).fillMaxHeight())
                ArtworkControls(
                    artwork = artwork,
                    onPrevious = previous,
                    onNext = next,
                    modifier = Modifier.weight(2f).verticalScroll(rememberScrollState())
                )
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ArtworkFrame(artwork, modifier = Modifier.weight(1f).fillMaxWidth())
                ArtworkControls(artwork, previous, next, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun ArtworkFrame(artwork: Artwork, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 3.dp
    ) {
        Image(
            painter = painterResource(artwork.image),
            contentDescription = stringResource(artwork.title),
            modifier = Modifier.fillMaxSize().padding(12.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
private fun ArtworkControls(
    artwork: Artwork,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(artwork.title),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(
                        R.string.artwork_credit,
                        stringResource(artwork.artist),
                        stringResource(artwork.year)
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = onPrevious, modifier = Modifier.weight(1f)) {
                Text(stringResource(R.string.previous_artwork))
            }
            Button(onClick = onNext, modifier = Modifier.weight(1f)) {
                Text(stringResource(R.string.next_artwork))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 780)
@Preview(showBackground = true, widthDp = 780, heightDp = 390)
@Composable
private fun ArtSpacePreview() {
    ArtSpaceAppTheme {
        Surface {
            ArtSpaceScreen()
        }
    }
}
