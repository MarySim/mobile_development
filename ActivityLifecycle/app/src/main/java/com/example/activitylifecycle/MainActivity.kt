package com.example.activitylifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.activitylifecycle.ui.theme.ActivityLifecycleTheme

class MainActivity : ComponentActivity() {
    private val instanceId = Integer.toHexString(System.identityHashCode(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle("onCreate")
        enableEdgeToEdge()
        setContent {
            ActivityLifecycleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LifecycleScreen()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("onResume")
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("onPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycle("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("onDestroy")
    }

    private fun logLifecycle(method: String) {
        Log.d(TAG, "$method [$instanceId]")
    }

    private companion object {
        const val TAG = "ActivityLifecycle"
    }
}

@Composable
private fun LifecycleScreen() {
    Column(
        modifier = Modifier.fillMaxSize().safeDrawingPadding().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.screen_title),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.logcat_instructions),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LifecyclePreview() {
    ActivityLifecycleTheme {
        LifecycleScreen()
    }
}
