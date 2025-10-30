package com.example.venuslogin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.venuslogin.ui.login.LoginScreen
import com.example.venuslogin.ui.register.RegisterScreen
import com.example.venuslogin.ui.home.HomeScreen
import com.example.venuslogin.ui.professionals.ProfessionalsScreen
import com.example.venuslogin.ui.reserva.ReservaScreen
import com.example.venuslogin.ui.historial.HistorialScreen
import com.example.venuslogin.ui.models.Profesional
import com.example.venuslogin.ui.models.Reserva
import com.example.venuslogin.ui.models.Usuario


@Composable
fun AppNavigation(
    profesionales: List<Profesional>,
    reservas: MutableList<Reserva>,
    usuarios: MutableList<Usuario>
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, usuarios) }
        composable("register") { RegisterScreen(navController, usuarios) }
        composable("home") { HomeScreen(navController) }

        composable("profesionales") {
            ProfessionalsScreen(profesionales) { prof ->
                navController.navigate("reserva/${prof.id}")
            }
        }

        composable(
            "reserva/{profId}",
            arguments = listOf(navArgument("profId") { type = NavType.IntType })
        ) { backStackEntry ->
            val profId = backStackEntry.arguments?.getInt("profId") ?: 0
            val prof = profesionales.find { it.id == profId }!!
            ReservaScreen(
                profesional = prof,
                onReservaConfirmada = { fecha, hora ->
                    // Cuando el usuario confirma, recibimos la fecha y la hora.
                    val nuevaReserva = Reserva(
                        id = (reservas.size + 1).toLong(), // ID simple, solo para ejemplo
                        profesionalNombre = prof.nombre,
                        especialidad = prof.especialidad,
                        fecha = fecha,
                        hora = hora,
                        estado = "Confirmada" // Puedes añadir estados (confirmada, cancelada, etc.)
                    )


                    reservas.add(nuevaReserva)

                    navController.popBackStack()
                }
            )
        }

        composable("historial") {
            // HistorialScreen ahora recibirá la lista actualizada cada vez
            HistorialScreen(reservas = reservas, profesionales = profesionales)
        }
    }
}