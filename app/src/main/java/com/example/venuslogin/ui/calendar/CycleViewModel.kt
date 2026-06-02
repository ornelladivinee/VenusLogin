package com.example.venuslogin.ui.calendar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.venuslogin.ui.models.CycleData

class CycleViewModel : ViewModel() {

    var cycleData by mutableStateOf(
        CycleData(
            lastPeriodDate = 1,
            cycleLength = 28,
            periodLength = 5
        )
    )
        private set

    fun updateCycle(
        lastPeriod: Int,
        cycleLength: Int,
        periodLength: Int
    ) {
        cycleData = CycleData(
            lastPeriodDate = lastPeriod,
            cycleLength = cycleLength,
            periodLength = periodLength
        )
    }
}