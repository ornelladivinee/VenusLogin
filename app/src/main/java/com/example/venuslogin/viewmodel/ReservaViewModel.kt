package com.example.venuslogin.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.venuslogin.repository.ReservaRepository
import com.example.venuslogin.ui.models.Reserva
import kotlinx.coroutines.launch

class ReservaViewModel(private val repository: ReservaRepository) : ViewModel() {

    // Estado de la lista de reservas
    var reservas by mutableStateOf<List<Reserva>>(emptyList())
        private set

    // Estado de carga y errores
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Obtener todas las reservas
    fun fetchReservas() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = repository.getReservas()
                if (response.isSuccessful) {
                    reservas = response.body() ?: emptyList()
                } else {
                    errorMessage = "Error ${response.code()}: ${response.message()}"
                }
            } catch (e: Exception) {
                errorMessage = "Excepción: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }

    // Agregar una nueva reserva
    fun addReserva(reserva: Reserva, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = repository.addReserva(reserva)
                if (response.isSuccessful) {
                    response.body()?.let { nueva ->
                        reservas = reservas + nueva
                        onSuccess()
                    } ?: run {
                        errorMessage = "Error al agregar reserva: cuerpo vacío"
                    }
                } else {
                    errorMessage = "Error ${response.code()}: ${response.message()}"
                }
            } catch (e: Exception) {
                errorMessage = "Excepción: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }

    // Eliminar reserva por ID
    fun deleteReserva(id: Long, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = repository.deleteReserva(id)
                if (response.isSuccessful) {
                    reservas = reservas.filter { it.id != id }
                    onSuccess()
                } else {
                    errorMessage = "Error ${response.code()}: ${response.message()}"
                }
            } catch (e: Exception) {
                errorMessage = "Excepción: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }
}
