package com.example.venuslogin.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.Instant
import java.time.ZoneId
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

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

        VenusDatePicker { day ->
            viewModel.updateCycle(
                lastPeriod = day,
                cycleLength = cycleData.cycleLength,
                periodLength = cycleData.periodLength
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🌸 CALENDARIO
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            items(days) { day ->

                val color = when (day.type) {
                    DayType.PERIOD -> Color(0xFFD81B60)
                    DayType.FERTILE -> Color(0xFFF8BBD0)
                    DayType.OVULATION -> Color(0xFFE91E63)
                    DayType.NORMAL -> Color.White
                }

                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(40.dp)
                        .background(color),
                    contentAlignment = Alignment.Center
                ) {
                    Text(day.day.toString())
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🌸 LEYENDA ABAJO
        Text("🌸 Fases del ciclo:", fontSize = 16.sp)

        LegendItem("🩸 Menstruación", Color(0xFFD81B60))
        LegendItem("🌸 Fase fértil", Color(0xFFF8BBD0))
        LegendItem("💜 Ovulación", Color(0xFFE91E63))
        LegendItem("🤍 Normal", Color.White)

        Spacer(modifier = Modifier.height(8.dp))
    }
}


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
                TextButton(onClick = {
                    val millis = datePickerState.selectedDateMillis

                    if (millis != null) {
                        val day = Instant.ofEpochMilli(millis)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                            .dayOfMonth

                        onDateSelected(day)
                    }

                    showPicker = false
                }) {
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

@Composable
fun LegendItem(text: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(text)
    }
}