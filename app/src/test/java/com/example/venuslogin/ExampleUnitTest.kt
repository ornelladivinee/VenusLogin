package com.example.venuslogin

import org.junit.Assert.*
import org.junit.Test

// --------------------------------------------------------
// PRUEBAS UNITARIAS DE CAJA BLANCA - MÓDULO LOGIN
// Estas pruebas validan la lógica interna del metodo
// validarCredenciales(), verificando diferentes casos.
// --------------------------------------------------------
class ExampleUnitTest {

    @Test
    fun validarCredenciales_correctas_retornaTrue() {
        // Caso de prueba: credenciales correctas
        val usuario = "venus"
        val contrasena = "1234"
        val resultado = validarCredenciales(usuario, contrasena)

        // Se espera true porque ambas coinciden con lo definido en el metodo
        assertTrue(resultado)
    }

    @Test
    fun validarCredenciales_incorrectas_retornaFalse() {
        // Caso de prueba: contraseña incorrecta
        val usuario = "venus"
        val contrasena = "abcd"
        val resultado = validarCredenciales(usuario, contrasena)

        // Se espera false porque la contraseña no coincide
        assertFalse(resultado)
    }

    // Lógica interna a validar (caja blanca)
    private fun validarCredenciales(usuario: String, contrasena: String): Boolean {
        return usuario == "venus" && contrasena == "1234"
    }
}


// --------------------------------------------------------
// PRUEBAS UNITARIAS DE CAJA BLANCA - MÓDULO RESERVAS
// Validan la lógica interna del gestor de reservas.
// --------------------------------------------------------

data class Reserva(val fecha: String, val motivo: String)

class GestorReservas {
    private val lista = mutableListOf<Reserva>()

    // Agrega la reserva solo si los campos no están vacíos
    fun agregarReserva(reserva: Reserva): Boolean {
        if (reserva.fecha.isEmpty() || reserva.motivo.isEmpty()) return false
        lista.add(reserva)
        return true
    }

    fun obtenerReservas(): List<Reserva> = lista
}

class ReservaUnitTest {

    @Test
    fun crearReserva_valida_retornaTrue() {
        // Caso de prueba: reserva con datos válidos
        val gestor = GestorReservas()
        val reserva = Reserva("2025-11-20", "Control médico")

        val resultado = gestor.agregarReserva(reserva)

        // Se espera que la reserva se agregue correctamente
        assertTrue(resultado)
        assertEquals(1, gestor.obtenerReservas().size)
    }

    @Test
    fun crearReserva_invalida_camposVacios_retornaFalse() {
        // Caso de prueba: reserva con campos vacíos
        val gestor = GestorReservas()
        val reserva = Reserva("", "")

        val resultado = gestor.agregarReserva(reserva)

        // Se espera false por validación interna
        assertFalse(resultado)
    }
}


// --------------------------------------------------------
// PRUEBAS UNITARIAS DE CAJA BLANCA - VALIDACIÓN DE CONTRASEÑA
// Validan reglas internas, como longitud mínima.
// --------------------------------------------------------
class PasswordValidatorTest {

    // Lógica interna que se prueba
    private fun validarLongitud(contrasena: String): Boolean {
        return contrasena.length >= 4
    }

    @Test
    fun contrasenaMuyCorta_retornaFalse() {
        // Caso de prueba: longitud insuficiente
        val resultado = validarLongitud("12")

        // Se espera false porque no cumple la longitud mínima
        assertFalse(resultado)
    }

    @Test
    fun contrasenaConLongitudCorrecta_retornaTrue() {
        // Caso de prueba: longitud válida
        val resultado = validarLongitud("1234")

        // Se espera true porque cumple el mínimo
        assertTrue(resultado)
    }
}
