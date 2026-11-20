package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val viewModel: ArtSpaceViewModel = viewModel()
                    val state by viewModel.uiState.collectAsStateWithLifecycle()
                    ArtSpaceScreen(state, viewModel::previous, viewModel::next)
                }
            }
        }
    }
}

@Composable
private fun ArtSpaceScreen(
    state: ArtSpaceUiState,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    val artwork = state.artwork
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
                    onPrevious = onPrevious,
                    onNext = onNext,
                    modifier = Modifier.weight(2f).verticalScroll(rememberScrollState())
                )
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ArtworkFrame(artwork, modifier = Modifier.weight(1f).fillMaxWidth())
                ArtworkControls(artwork, onPrevious, onNext, modifier = Modifier.fillMaxWidth())
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
            ArtSpaceScreen(artSpaceState(), onPrevious = {}, onNext = {})
        }
    }
}
