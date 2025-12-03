package com.example.venuslogin.ui.professionals

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.venuslogin.ui.models.Profesional
import androidx.compose.material.icons.automirrored.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfessionalsScreen(
    navController: NavHostController,
    profesionales: List<Profesional>
) {
    val venusPink = Color(0xFFD81B60)
    val lightPinkBg = Color(0xFFFCE4EC)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightPinkBg)
    ) {
        // --- TopAppBar con flecha para volver ---
        TopAppBar(
            title = { Text("Profesionales", color = venusPink) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = venusPink)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = lightPinkBg)
        )

        // --- Lista de profesionales ---
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(profesionales) { prof ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            // Navega a la pantalla de reserva pasando el ID del profesional
                            navController.navigate("reserva/${prof.id}")
                        },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = prof.nombre, style = MaterialTheme.typography.titleMedium)
                        Text(text = prof.especialidad, style = MaterialTheme.typography.bodyMedium)
                        Text(
                            text = "Disponibilidad: ${prof.horasDisponibles.joinToString()}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
