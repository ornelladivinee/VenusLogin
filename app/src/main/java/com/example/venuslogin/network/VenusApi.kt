package com.example.venuslogin.network

import com.example.venuslogin.ui.models.Cita
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface VenusApiService {
    @GET("random") // La parte final de la URL (endpoint)
    suspend fun obtenerCitaDelDia(): List<Cita> // La API devuelve una lista
}

// 2. El Objeto Cliente (El Mesero): Configura la conexión
object RetrofitClient {
    private const val BASE_URL = "https://zenquotes.io/api/"

    val servicio: VenusApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VenusApiService::class.java)
    }
}