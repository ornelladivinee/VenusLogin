package com.example.venuslogin.ui.calendar

import com.example.venuslogin.ui.models.CycleData
import com.example.venuslogin.ui.calendar.DayType

data class CycleDay(
    val day: Int,
    val type: DayType
)

fun generateCycle(data: CycleData): List<CycleDay> {

    val days = mutableListOf<CycleDay>()

    val cycleLength = data.cycleLength.coerceAtLeast(1)
    val periodLength = data.periodLength.coerceAtLeast(1)

    val periodStart = data.lastPeriodDate.coerceIn(1, cycleLength)
    val periodEnd = (periodStart + periodLength - 1).coerceAtMost(cycleLength)

    val ovulationDay = (cycleLength / 2).coerceAtLeast(1)
    val fertileStart = (ovulationDay - 3).coerceAtLeast(1)
    val fertileEnd = (ovulationDay + 2).coerceAtMost(cycleLength)

    for (i in 1..cycleLength) {

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