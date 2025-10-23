package com.example.venuslogin.ui.historial

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.venuslogin.ui.models.Reserva

@Composable
fun HistorialScreen(reservas: List<Reserva>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(reservas) { reserva ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Profesional ID: ${reserva.profesionalId}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Fecha y Hora: ${reserva.fechaHora}", style = MaterialTheme.typography.bodySmall)
                    Text(text = "Estado: ${reserva.estado}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

