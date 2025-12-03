package com.example.venuslogin.network

import com.example.venuslogin.ui.models.Reserva
import retrofit2.Response
import retrofit2.http.*

interface ReservaApi {

    @GET("reservas")
    suspend fun getReservas(): Response<List<Reserva>>

    @GET("reservas/{id}")
    suspend fun getReservaById(@Path("id") id: Long): Response<Reserva>

    @POST("reservas")
    suspend fun addReserva(@Body reserva: Reserva): Response<Reserva>

    @DELETE("reservas/{id}")
    suspend fun deleteReserva(@Path("id") id: Long): Response<Unit>
}
