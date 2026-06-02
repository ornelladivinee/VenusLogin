package com.example.venuslogin.ui.calendar

import com.example.venuslogin.ui.models.*
import com.example.venuslogin.ui.models.CycleDay
import com.example.venuslogin.ui.calendar.DayType
import com.example.venuslogin.ui.models.CycleData

enum class DayType {
    PERIOD,
    FERTILE,
    OVULATION,
    NORMAL
}

data class CycleDay(
    val day: Int,
    val type: DayType
)

fun generateCycle(data: CycleData): List<CycleDay> {

    val days = mutableListOf<CycleDay>()

    val periodStart = data.lastPeriodDate
    val periodEnd = periodStart + data.periodLength - 1

    val ovulationDay = data.cycleLength / 2
    val fertileStart = ovulationDay - 3
    val fertileEnd = ovulationDay + 2

    for (i in 1..data.cycleLength) {

        val type = when {
            i in periodStart..periodEnd -> DayType.PERIOD

            i == ovulationDay -> DayType.OVULATION

            i in fertileStart..fertileEnd -> DayType.FERTILE

            else -> DayType.NORMAL
        }

        days.add(CycleDay(i, type))
    }

    return days
}