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

@Composable
fun HistorialScreen(
    reservas: List<Reserva>,
    profesionales: List<Profesional>
) {
    val venusPink = Color(0xFFD81B60)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCE4EC)) //Light Pink Accent
            .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp)
    ) {
        item {
            Text(
                text = "Tu Historial de Citas",
                style = MaterialTheme.typography.headlineMedium,
                color = venusPink, // --- CAMBIO 2: Título en rosa ---
                modifier = Modifier.padding(bottom = 24.dp, start = 8.dp)
            )
        }

        items(reservas) { reserva ->
            val profesional = profesionales.find { it.id.toLong() == reserva.id}
            val nombreProfesional = profesional?.nombre ?: "Profesional Desconocido"
            val especialidadProfesional = profesional?.especialidad ?: "Especialidad no definida"

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF8BBD0)) // Un rosa medio

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
                        color = if (reserva.estado == "Confirmada") Color(0xFF00C853) else venusPink // Verde esmeralda o rosa
                    )
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
            Profesional(1, "Dra. Laura Montes", "Cardiología", listOf("09:00", "10:00")),
            Profesional(2, "Dr. Javier Ruiz", "Dermatología", listOf("14:00", "15:00"))
        )
        val reservasPreview = listOf(
            Reserva(101, "Dra. Lopez", "Ginecología", "01/11/2023", "09:00","Confirmada"),
            Reserva(102, "Dra. Perez", "Endocrinología", "02/11/2023", "14:00","Cancelada"),
            Reserva(103, "Dra. Mesina", "Cardiología", "03/11/2023", "10:00","Confirmada")
        )
        HistorialScreen(reservas = reservasPreview, profesionales = profesionalesPreview)
    }
}