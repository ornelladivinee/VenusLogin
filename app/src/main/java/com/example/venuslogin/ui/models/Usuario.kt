package com.example.venuslogin.ui.models

data class Usuario(
    val id: Int,
    val nombre: String,
    val correo: String,
    val rol: String // "paciente" o "administrador"
)