package com.example.gotapp.ui.main
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.example.gotapp.themes.CustomTheme
import com.example.gotapp.themes.lightColors
import com.example.gotapp.ui.navigation.LocalNavController
import com.example.gotapp.ui.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTheme(colors = lightColors()) {
                CompositionLocalProvider(
                    LocalNavController provides rememberNavController()
                ) {
                    Navigation()
                }
            }
            }
            }
        }

