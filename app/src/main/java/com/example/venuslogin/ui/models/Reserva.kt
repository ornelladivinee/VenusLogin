package com.example.venuslogin.ui.models

data class Reserva(
    val id: Long,
    val profesionalNombre: String,
    val especialidad: String,
    val fecha: String,
    val hora: String,
    val estado: String
)
