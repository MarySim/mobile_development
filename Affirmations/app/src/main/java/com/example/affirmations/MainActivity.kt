package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val affirmations = Datasource().loadAffirmations()
        setContent {
            AffirmationsTheme {
                AffirmationsApp(affirmations)
            }
        }
    }
}

@Composable
fun AffirmationsApp(affirmations: List<Affirmation>, modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        AffirmationList(
            affirmationList = affirmations,
            modifier = Modifier.fillMaxSize().safeDrawingPadding()
        )
    }
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(affirmationList, key = { it.stringResourceId }) { affirmation ->
            AffirmationCard(affirmation, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    val text = stringResource(affirmation.stringResourceId)
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = text,
                modifier = Modifier.fillMaxWidth().height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = text,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AffirmationCardPreview() {
    AffirmationsTheme {
        AffirmationCard(
            affirmation = Affirmation(R.string.affirmation_1, R.drawable.image1),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
    }
}
