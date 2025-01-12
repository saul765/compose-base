package com.example.composebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composebase.ui.ComposeBaseApp
import com.example.composebase.ui.rememberComposeBaseNavigationState
import com.example.composebase.ui.theme.ComposeBaseTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private val splashScreen by lazy { installSplashScreen() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition {
            false
        }

        setContent {
            KoinContext {
                ComposeBaseTheme {
                    val navControllerState = rememberComposeBaseNavigationState()

                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                    ComposeBaseApp(navControllerState, uiState)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeBaseTheme {
        Greeting("Android")
    }
}