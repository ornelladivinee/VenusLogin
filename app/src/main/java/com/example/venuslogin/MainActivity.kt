package com.example.venuslogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.venuslogin.ui.navigation.AppNavigation
import com.example.venuslogin.ui.theme.VenusLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VenusLoginTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White //fondo blanco
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

