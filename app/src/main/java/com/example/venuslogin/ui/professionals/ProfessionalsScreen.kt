package com.example.venuslogin.ui.professionals

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.venuslogin.ui.models.Profesional



@Composable
fun ProfessionalsScreen(
    profesionales: List<Profesional>,
    onReservaClick: (Profesional) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(profesionales) { prof ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onReservaClick(prof) },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = prof.nombre, style = MaterialTheme.typography.titleMedium)
                    Text(text = prof.especialidad, style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Disponibilidad: ${prof.disponibilidad.joinToString()}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
