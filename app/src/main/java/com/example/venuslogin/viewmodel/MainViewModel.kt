package com.example.venuslogin.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.venuslogin.ui.models.Usuario

class MainViewModel : ViewModel() {

    var mensaje by mutableStateOf("")
        private set

    var isLoggedIn by mutableStateOf(false)
        private set

    // Validar login
    fun login(usuarioIngresado: String, clave: String, usuarios: List<Usuario>) {
        val usuarioEncontrado = usuarios.find {
            (it.nombre == usuarioIngresado || it.correo == usuarioIngresado) && it.clave == clave
        }

        if (usuarioEncontrado != null) {
            mensaje = ""
            isLoggedIn = true
        } else {
            val listaDeNombres = usuarios.joinToString(", ") { it.nombre }
            mensaje = "Error. Lista: [$listaDeNombres]. Tú escribiste: [$usuarioIngresado] y [$clave]"
            isLoggedIn = false
        }
    }

    fun logout() {
        isLoggedIn = false
    }
}
