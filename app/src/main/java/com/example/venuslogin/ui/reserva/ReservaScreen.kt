package com.example.venuslogin.ui.reserva

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.venuslogin.ui.models.Profesional
import com.example.venuslogin.ui.theme.VenusLoginTheme
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservaScreen(
    profesional: Profesional,
    onReservaConfirmada: (fecha: String, hora: String) -> Unit
) {
    val venusPink = Color(0xFFD81B60)
    val lightPinkBg = Color(0xFFFCE4EC)

    // --- Estados para guardar las selecciones ---
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var isTimeDropdownExpanded by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightPinkBg) // <-- CAMBIO: Fondo rosa claro
            .padding(16.dp)
    ) {
        // --- 2. Títulos en rosa ---
        Text(
            text = "Reservar con ${profesional.nombre}",
            style = MaterialTheme.typography.headlineSmall,
            color = venusPink // <-- CAMBIO: Color de título
        )
        Text(
            text = profesional.especialidad,
            style = MaterialTheme.typography.titleMedium,
            color = venusPink.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- 3. Campo de Fecha (estilo rosa) ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { showDatePicker = true })
        ) {
            OutlinedTextField(
                value = selectedDate,
                onValueChange = { },
                label = { Text("Fecha de la cita") },
                placeholder = { Text("Selecciona una fecha") },
                leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Calendario") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                enabled = false,

                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledBorderColor = venusPink.copy(alpha = 0.5f), // Borde rosa
                    disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledLabelColor = venusPink, // Etiqueta rosa
                    disabledLeadingIconColor = venusPink // Icono rosa
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
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                readOnly = true,

                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedIndicatorColor = venusPink,
                    focusedLabelColor = venusPink,
                    focusedTrailingIconColor = venusPink,
                    unfocusedIndicatorColor = venusPink,
                    unfocusedLabelColor = venusPink,
                    unfocusedTrailingIconColor = venusPink
                )
            )

            // Contenido del menú desplegable
            ExposedDropdownMenu(
                expanded = isTimeDropdownExpanded,
                onDismissRequest = { isTimeDropdownExpanded = false }
            ) {
                profesional.horasDisponibles.forEach { hora ->
                    DropdownMenuItem(
                        text = { Text(hora) },
                        onClick = {
                            selectedTime = hora
                            isTimeDropdownExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))


        Button(
            onClick = {
                onReservaConfirmada(selectedDate, selectedTime)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = selectedDate.isNotEmpty() && selectedTime.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = venusPink,
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
                        val millis = datePickerState.selectedDateMillis
                        if (millis != null) {
                            selectedDate = dateFormatter.format(Date(millis))
                        }
                        showDatePicker = false
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = venusPink)
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDatePicker = false },
                    colors = ButtonDefaults.textButtonColors(contentColor = venusPink.copy(alpha = 0.7f))
                ) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(
                state = datePickerState,

                colors = DatePickerDefaults.colors(
                    containerColor = lightPinkBg.copy(alpha = 1.0f),
                    titleContentColor = venusPink,
                    headlineContentColor = venusPink,
                    weekdayContentColor = venusPink,
                    selectedDayContainerColor = venusPink,
                    selectedDayContentColor = Color.White,
                    todayDateBorderColor = venusPink,
                    todayContentColor = venusPink
                )
            )
        }
    }
} // --- Fin de la función ReservaScreen ---

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
            onReservaConfirmada = { _, _ -> }
        )
    }
}