package com.example.venuslogin.ui.historial

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.venuslogin.ui.models.Profesional
import com.example.venuslogin.ui.models.Reserva
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.venuslogin.ui.theme.VenusLoginTheme
import androidx.compose.foundation.background
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.navigation.NavHostController
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen(
    navController: NavHostController,
    reservas: List<Reserva>,
    profesionales: List<Profesional>
) {
    val venusPink = Color(0xFFD81B60)

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


            items(reservas) { reserva ->
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
            profesionales = profesionalesPreview)
    }
}