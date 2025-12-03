package com.example.venuslogin.repository

import com.example.venuslogin.network.ReservaApi
import com.example.venuslogin.ui.models.Reserva
import retrofit2.Response

class ReservaRepository(private val api: ReservaApi) {

    // Obtener todas las reservas
    suspend fun getReservas(): Response<List<Reserva>> {
        return api.getReservas()
    }

    // Obtener reserva por ID
    suspend fun getReservaById(id: Long): Response<Reserva> {
        return api.getReservaById(id)
    }

    // Agregar una nueva reserva
    suspend fun addReserva(reserva: Reserva): Response<Reserva> {
        return api.addReserva(reserva)
    }

    // Eliminar reserva por ID
    suspend fun deleteReserva(id: Long): Response<Unit> {
        return api.deleteReserva(id)
    }
}

