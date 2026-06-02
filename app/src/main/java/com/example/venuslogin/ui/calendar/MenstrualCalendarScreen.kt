package com.example.venuslogin.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.venuslogin.ui.models.CycleData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.venuslogin.ui.calendar.CycleViewModel
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.Dialog
import java.time.Instant
import java.time.ZoneId

// IMPORTS IMPORTANTES
import com.example.venuslogin.ui.models.CycleDay
import com.example.venuslogin.ui.calendar.generateCycle
import com.example.venuslogin.ui.calendar.DayType

@Composable
fun MenstrualCalendarScreen() {

    val viewModel: CycleViewModel = viewModel()

    val cycleData = viewModel.cycleData
    val days = generateCycle(cycleData)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("🌸 Calendario Menstrual Venus", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(12.dp))

        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun VenusDatePicker(
            onDateSelected: (Int) -> Unit
        ) {
            var showPicker by remember { mutableStateOf(false) }

            val datePickerState = rememberDatePickerState()

            Button(onClick = { showPicker = true }) {
                Text("Seleccionar fecha")
            }

            if (showPicker) {

                DatePickerDialog(
                    onDismissRequest = { showPicker = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                val selectedDate = datePickerState.selectedDateMillis

                                if (selectedDate != null) {
                                    val day = Instant.ofEpochMilli(selectedDate)
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate()
                                        .dayOfMonth

                                    onDateSelected(day)
                                }

                                showPicker = false
                            }
                        ) {
                            Text("Confirmar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showPicker = false }) {
                            Text("Cancelar")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }
    }
}