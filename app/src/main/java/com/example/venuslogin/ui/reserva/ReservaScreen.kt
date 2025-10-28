package com.example.venuslogin.ui.reserva

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.venuslogin.ui.models.Profesional
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.venuslogin.ui.theme.VenusLoginTheme
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservaScreen(
    profesional: Profesional,
    onReservaConfirmada: (fecha: String, hora: String) -> Unit
) {
// --- Estados para guardar las selecciones ---
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var isTimeDropdownExpanded by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    // Formateador para convertir la fecha del DatePicker (que es un Long) a un String
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault(
    ))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Reservar con ${profesional.nombre}",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = profesional.especialidad,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    // 1. El clic se pone en el Box
                    onClick = { showDatePicker = true }
                )
        ) {
            OutlinedTextField(
                value = selectedDate,
                onValueChange = { },
                label = { Text("Fecha de la cita") },
                placeholder = { Text("Selecciona una fecha") },
                leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Calendario") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,

                // 2. Deshabilita el campo para que el Box reciba el clic
                enabled = false,

                // 3. Colores para que no se vea gris (deshabilitado)
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledBorderColor = MaterialTheme.colorScheme.outline,
                    disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = isTimeDropdownExpanded,
            onExpandedChange = { isTimeDropdownExpanded = !isTimeDropdownExpanded }
        ) {
            OutlinedTextField(
                value = selectedTime,
                onValueChange = {},
                label = { Text("Hora disponible") },
                placeholder = { Text("Selecciona una hora") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isTimeDropdownExpanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(), // Ancla el menú al campo de texto
                readOnly = true
            )

            // Contenido del menú desplegable
            ExposedDropdownMenu(
                expanded = isTimeDropdownExpanded,
                onDismissRequest = { isTimeDropdownExpanded = false }
            ) {
                // Itera sobre las horas que pasaste en el objeto Profesional
                profesional.horasDisponibles.forEach { hora ->
                    DropdownMenuItem(
                        text = { Text(hora) },
                        onClick = {
                            selectedTime = hora // Guarda la hora seleccionada
                            isTimeDropdownExpanded = false // Cierra el menú
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --- BOTÓN DE CONFIRMAR ---
        Button(
            onClick = {

                onReservaConfirmada(selectedDate, selectedTime)
            },
            modifier = Modifier.fillMaxWidth(),
            // El botón solo se activa si se seleccionó fecha Y hora
            enabled = selectedDate.isNotEmpty() && selectedTime.isNotEmpty(),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD81B60),
                contentColor = Color.White
            )


        ) {
            Text("Confirmar Reserva")
        }
    }

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Confirma la selección de fecha
                        val millis = datePickerState.selectedDateMillis
                        if (millis != null) {
                            selectedDate = dateFormatter.format(Date(millis))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ReservaScreenPreview() {

    val profesionalEjemplo = Profesional(
        id = 1,
        nombre = "Dra. Ana López",
        especialidad = "Ginecología",
        horasDisponibles = listOf("09:00", "10:00", "11:00")
    )

    VenusLoginTheme {
        ReservaScreen(
            profesional = profesionalEjemplo,
            onReservaConfirmada = { _, _ -> } // Lambda vacía
        )
    }
}