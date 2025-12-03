package com.example.venuslogin.ui.historial

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.venuslogin.ui.models.Profesional
import com.example.venuslogin.ui.models.Reserva
import com.example.venuslogin.ui.theme.VenusLoginTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen(
    navController: NavHostController,
    reservas: List<Reserva>, // lista original
    profesionales: List<Profesional>
) {
    val venusPink = Color(0xFFD81B60)
    val context = LocalContext.current

    // --- Estado observable para que Compose reaccione a cambios en la lista ---
    val reservasState = remember { mutableStateListOf<Reserva>().apply { addAll(reservas) } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tu Historial de Citas", color = venusPink) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = venusPink)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFCE4EC))
            )
        },
        containerColor = Color(0xFFFCE4EC)
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp)
        ) {
            items(reservasState, key = { it.id }) { reserva -> // <-- clave para optimización
                val profesional = profesionales.find { it.id.toLong() == reserva.id }
                val nombreProfesional = profesional?.nombre ?: "Profesional Desconocido"
                val especialidadProfesional = profesional?.especialidad ?: "Especialidad no definida"

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8BBD0))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = nombreProfesional,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = venusPink
                        )
                        Text(
                            text = especialidadProfesional,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.DarkGray
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Fecha: ${reserva.fecha}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                        Text(
                            text = "Estado: ${reserva.estado}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (reserva.estado == "Confirmada") Color(0xFF00C853) else venusPink
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // --- Botón para borrar la reserva ---
                        Button(
                            onClick = {
                                reservasState.remove(reserva) // elimina y Compose actualiza la UI automáticamente
                                Toast.makeText(context, "Cita borrada con éxito", Toast.LENGTH_SHORT).show()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = venusPink,
                                contentColor = Color.White
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Borrar Cita")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistorialScreenPreview() {
    VenusLoginTheme {
        val profesionalesPreview = listOf(
            Profesional(1, "Dra. Laura Montes", "Ginecología", listOf("09:00", "10:00")),
            Profesional(2, "Dr. Javiera Ruiz", "Dermatología", listOf("14:00", "15:00"))
        )
        val reservasPreview = listOf(
            Reserva(101, "Dra. Lopez", "Ginecología", "01/11/2023", "09:00","Confirmada"),
            Reserva(102, "Dra. Perez", "Endocrinología", "02/11/2023", "14:00","Cancelada")
        )
        HistorialScreen(
            navController = rememberNavController(),
            reservas = reservasPreview,
            profesionales = profesionalesPreview
        )
    }
}
