package com.example.venuslogin.ui.models

import com.google.gson.annotations.SerializedName

data class Cita(
    // el @SerializedName le dice a Gson: "El campo 'q' del JSON pónmelo en 'frase'"
    @SerializedName("q") val frase: String,
    @SerializedName("a") val autor: String
)