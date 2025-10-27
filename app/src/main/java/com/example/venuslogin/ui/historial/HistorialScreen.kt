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
                    Text(text = "Profesional ID: ${reserva.id}", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Especialidad: ${reserva.especialidad}", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Fecha: ${reserva.fecha}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Hora: ${reserva.hora}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Estado: ${reserva.estado}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
