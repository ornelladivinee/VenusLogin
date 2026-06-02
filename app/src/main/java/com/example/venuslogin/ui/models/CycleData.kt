package com.example.venuslogin.ui.models

data class CycleData(
    val lastPeriodDate: Int,
    val cycleLength: Int = 28,
    val periodLength: Int = 5
)