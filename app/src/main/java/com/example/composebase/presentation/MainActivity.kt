package com.example.composebase.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.composebase.presentation.theme.ComposeBaseTheme

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
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
                    ComposeBaseApp(
                        navigationState = navControllerState
                    )
                }
            }
        }
    }
}
