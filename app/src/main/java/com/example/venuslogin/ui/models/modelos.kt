package com.example.venuslogin.ui.models

data class Usuario(
    val id: Int,
    val nombre: String,
    val correo: String,
    val rol: String // "paciente" o "administrador"
)

data class Profesional(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val disponibilidad: List<String>
)

data class Reserva(
    val id: Int,
    val pacienteId: Int,
    val profesionalId: Int,
    val fechaHora: String,
    val estado: String // "confirmada", "cancelada"
)
