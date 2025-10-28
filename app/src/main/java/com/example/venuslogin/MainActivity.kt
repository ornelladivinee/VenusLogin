package com.example.venuslogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.venuslogin.ui.models.Profesional
import com.example.venuslogin.ui.models.Reserva
import com.example.venuslogin.ui.models.Usuario
import com.example.venuslogin.ui.navigation.AppNavigation
import com.example.venuslogin.ui.theme.VenusLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val profesionales = listOf(
            Profesional(1, "Dra. López", "Ginecología", listOf("09:00", "10:00")),
            Profesional(2, "Dra. Pérez", "Endocrinología", listOf("11:00", "12:00"))
        )

        setContent {
            VenusLoginTheme {

                val reservas = remember { mutableStateListOf<Reserva>() }
                val usuarios = remember { mutableStateListOf<Usuario>() }


                if (usuarios.isEmpty()) {
                    usuarios.add(Usuario(1, "venus", "venus@gmail.com", "123456", "paciente"))
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White // O MaterialTheme.colorScheme.background
                ) {

                    AppNavigation(
                        profesionales = profesionales,
                        reservas = reservas,
                        usuarios = usuarios
                    )
                }
            }
        }
    }
}