package com.example.venuslogin.ui.models


data class Profesional(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val horasDisponibles: List<String>
)