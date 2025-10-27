package com.example.venuslogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.venuslogin.ui.navigation.AppNavigation
import com.example.venuslogin.ui.theme.VenusLoginTheme
import com.example.venuslogin.ui.models.Profesional
import com.example.venuslogin.ui.models.Reserva

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Datos de ejemplo
        val profesionales = listOf(
            Profesional(1, "Dra. López", "Ginecología", listOf("09:00", "10:00")),
            Profesional(2, "Dra. Pérez", "Endocrinología", listOf("11:00", "12:00"))
        )

        setContent {
            VenusLoginTheme {
                val reservas = remember { mutableStateListOf<Reserva>() }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    AppNavigation(profesionales, reservas)
                }
            }
        }
    }
}
